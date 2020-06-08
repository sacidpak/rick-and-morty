package com.rickandmorty.report;

import com.google.gson.Gson;
import com.rickandmorty.data.report.Log;
import com.rickandmorty.data.report.LogDTO;
import com.rickandmorty.data.report.LogMapper;
import com.rickandmorty.data.report.LogRepository;
import com.rickandmorty.response.ReportBaseInfo;
import com.rickandmorty.response.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Log neslerinin business işlemlerinin yapıldığı servis classı.
 * LogRepository ile işlemlerin yapıldığı dataların işlendiği yerdir.
 *
 * @version 1.0
 * @author sacidpak
 */
@Service
@RequiredArgsConstructor
public class ReportService<T> {
    private final LogRepository logRepository;
    private final EntityManager entityManager;

    /**
     * Controller classlarında bulunan methodların çağıdığı method.
     * Aşağıda belirtilen parametlerin tbl_log tablosuna kayıt edildiği yerdir.
     * @param endpointName
     * @param requestHeader
     * @param requestBody
     * @param responseHeader
     * @param responseBody
     */
    public void saveLog(String endpointName,
                        String requestHeader,String requestBody,
                        String responseHeader,T responseBody){
        Log logData = new Log();
        logData.setName(endpointName);
        logData.setRequestHeader(requestHeader);
        logData.setRequestBody(requestBody);
        logData.setResponseHeader(responseHeader);
        Gson gson = new Gson();
        logData.setResponseBody(gson.toJson(responseBody));
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        logData.setRequestTime(dateFormat.format(new Date()));

        logRepository.save(logData);
    }

    /**
     * ReportController'dan çağırılan method.ReportBaseInfo nesnesine endpointlerin
     * kaç defa çağırıldığı set edilir.Daha sonra ReportResponse
     * List<ReportBaseInfo> reportInfo listesine set edilir.
     * Ardından daha önce kayıt edilmiş log nesneleri tbl_log dosyasından çekilerek ReportResponse
     * classında bulunan List<LogDTO> report listesine set edilir.
     * @return
     */
    public ReportResponse getLogs(){
        ReportResponse reportResponse = new ReportResponse();

        //Endpoinlerin kaç defa çağırıldığını bulmak için yapılan işlem
        Session session = getCurrentSession();
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.groupProperty("name"))
                .add(Projections.rowCount());
        Criteria criteria  = session.createCriteria(Log.class);
        criteria.setProjection(projectionList);
        List<Object[]> endpointCount = criteria.list();

        List<ReportBaseInfo> repotInfo = new ArrayList<>();
        for (Object[] object : endpointCount) {
            ReportBaseInfo baseInfo = new ReportBaseInfo();
            baseInfo.setEndPointName(object[0].toString());
            baseInfo.setEndPointCallingCount(object[1].toString());

            repotInfo.add(baseInfo);
        }
        reportResponse.setReportInfo(repotInfo);

        //Tüm log bilgilerinin getirildiği kısım
        List<Log> logList = logRepository.findAll();
        List<LogDTO> logDTOList = new ArrayList<>();
        logList.forEach(logs -> {
            logDTOList.add(LogMapper.INSTANCE.to(logs));
        });
        reportResponse.setReport(logDTOList);

        return reportResponse;
    }

    /**
     * Endpointlerin kaç defa çağırıldığının bulunması için gereken işlemde
     * gerekli olan session nesnesine dönen method.
     * @return
     */
    protected Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }
}
