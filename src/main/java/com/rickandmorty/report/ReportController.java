package com.rickandmorty.report;

import com.rickandmorty.data.character.CharacterDTO;
import com.rickandmorty.data.report.LogDTO;
import com.rickandmorty.response.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Report sayfasının mapping işlemi yapılıdığı yönlendirici class.
 *
 * @version 1.0
 * @author sacidpak
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    /**
     * Report datalarının reportService ile alındığı GET methodu.
     * @return ResponseEntity<ReportResponse>
     */
    @GetMapping
    public ResponseEntity<ReportResponse> getReport(){
        return new ResponseEntity<ReportResponse>(reportService.getLogs(),new HttpHeaders(), HttpStatus.OK);
    }
}
