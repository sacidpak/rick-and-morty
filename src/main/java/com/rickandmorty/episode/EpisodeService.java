package com.rickandmorty.episode;

import com.rickandmorty.data.episode.Episode;
import com.rickandmorty.data.episode.EpisodeMapper;
import com.rickandmorty.data.episode.EpisodeDTO;
import com.rickandmorty.data.episode.EpisodeRepository;
import com.rickandmorty.response.PagingInfo;
import com.rickandmorty.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Episode neslerinin business işlemlerinin yapıldığı servis classı.
 * Episode ile işlemlerin yapıldığı dataların işlendiği yerdir.
 *
 * @version 1.0
 * @author sacidpak
 */
@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    @Value("${server.port}")
    private String port;

    /**
     * Id numarasına göre EpisodeRepository den dataların çekildiği daha
     * sonra Episode entity sınıfının EpisodeDTO ya mapping edildiği method.
     * @param id
     * @return EpisodeMapper ile mapping edilmiş EpisodeDTO nesnesi
     */
    public EpisodeDTO getEpisode (long id){
        Episode episode = episodeRepository.findById(id).orElse(null);
        return EpisodeMapper.INSTANCE.to(episode);
    }

    /**
     * Tüm Episode nesnelerinin EpisodeRepository ile çekildiği daha sonra
     * Paging edilmiş dataların toplam sayfa sayısı, data sayılarının vb. CustomResponse sınıfının
     * PaginInfo nesnesi set edilildiği ardından Paging edilmiş dataların
     * EpisodeDTO nesnesine mapping işleminin yapıldığı method.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return CustomResponse<EpisodeDTO> episodeCustomResponse
     */
    public CustomResponse<EpisodeDTO> getAll(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Episode> pagedResult = episodeRepository.findAll(paging);

        CustomResponse<EpisodeDTO> episodeCustomResponse = new CustomResponse<>();
        PagingInfo info = new PagingInfo();

        if (pagedResult.hasContent()){
            info.setCount(pagedResult.getTotalElements());
            info.setPage(pagedResult.getTotalPages());
            int pageNumber = pagedResult.getNumber() + 1 ;
            if(pageNumber < pagedResult.getTotalPages()) {
                info.setNextUrl(
                        "http://localhost:" + port + "/rickandmorty/episode?pageNo=" + (pageNumber + 1)
                );
            }else{
                info.setNextUrl("");
            }
            if(pageNumber > 1) {
                info.setPrevUrl(
                        "http://localhost:" + port + "/rickandmorty/episode?pageNo=" + (pageNumber - 1)
                );
            }else{
                info.setPrevUrl("");
            }

            List<EpisodeDTO> episodeDTOList = new ArrayList<>();
            pagedResult.forEach(episode -> {
                episodeDTOList.add(EpisodeMapper.INSTANCE.to(episode));
            });

            episodeCustomResponse.setInfo(info);
            episodeCustomResponse.setResult(episodeDTOList);
        }else{
            info.setCount(0);
            info.setPage(0);
            info.setPrevUrl("");
            info.setNextUrl("");
            episodeCustomResponse.setInfo(info);
        }

        return episodeCustomResponse;
    }
}
