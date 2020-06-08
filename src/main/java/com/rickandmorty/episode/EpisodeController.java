package com.rickandmorty.episode;

import com.rickandmorty.data.episode.EpisodeDTO;
import com.rickandmorty.report.ReportService;
import com.rickandmorty.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Episode sayfalarının mapping işlemi yapılıdığı yönlendirici class.
 *
 * @version 1.0
 * @author sacidpak
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/episode")
public class EpisodeController {
    private final EpisodeService episodeService;
    private final ReportService reportService;

    /**
     * Tüm episode listesinin gösterildiği method.Paging işlemi yapılmaktadır.
     * Ayrıca report çıktısı için saveLog methodu çağırılmaktadır.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param requestHeader
     * @return ResponseEntity<CustomResponse<EpisodeDTO>> ile response vermektedir.
     */
    @GetMapping
    public ResponseEntity<CustomResponse<EpisodeDTO>> getAll(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestHeader(value="Accept") String requestHeader)
    {
        CustomResponse<EpisodeDTO> customResponse = episodeService.getAll(pageNo-1, pageSize, sortBy);
        HttpHeaders responseHeader = new HttpHeaders();
        reportService.saveLog("/episode?page="+pageNo,
                requestHeader,"",
                responseHeader.toString(),customResponse);

        return new ResponseEntity<CustomResponse<EpisodeDTO>>(customResponse, responseHeader, HttpStatus.OK);
    }

    /**
     * Id numarası ile tek bir episode getiren method.
     * Ayrıca report çıktısı için saveLog methodu çağırılmaktadır.
     * @param id
     * @param requestHeader
     * @return ResponseEntity<EpisodeDTO> ile response dönmektedir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EpisodeDTO> getEpisode(@PathVariable long id,@RequestHeader(value="Accept") String requestHeader){
        EpisodeDTO episodeDTO = episodeService.getEpisode(id);
        HttpHeaders responseHeader = new HttpHeaders();
        reportService.saveLog("/episode/"+id,
                requestHeader,"",
                responseHeader.toString(),episodeDTO);

        return new ResponseEntity<EpisodeDTO>(episodeDTO,responseHeader, HttpStatus.OK);
    }
}
