package com.rickandmorty.character;

import com.rickandmorty.data.character.CharacterDTO;
import com.rickandmorty.report.ReportService;
import com.rickandmorty.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Character sayfalarının mapping işlemi yapılıdığı yönlendirici class.
 *
 * @version 1.0
 * @author sacidpak
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;
    private final ReportService reportService;

    /**
     * Tüm character listesinin gösterildiği method.Paging işlemi yapılmaktadır.
     * Ayrıca report çıktısı için saveLog methodu çağırılmaktadır.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param requestHeader
     * @return ResponseEntity ile response vermektedir.
     */
    @GetMapping
    public ResponseEntity<CustomResponse<CharacterDTO>> getAllCharacter(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestHeader(value="Accept") String requestHeader)
    {
        CustomResponse<CharacterDTO> customResponse = characterService.getAllCharacter(pageNo-1, pageSize, sortBy);
        HttpHeaders responseHeader = new HttpHeaders();
        reportService.saveLog("/character?page="+pageNo,
                                requestHeader,"",
                                responseHeader.toString(),customResponse);

        return new ResponseEntity<CustomResponse<CharacterDTO>>(customResponse, responseHeader, HttpStatus.OK);
    }

    /**
     * Id numarası ile tek bir character getiren method.
     * Ayrıca report çıktısı için saveLog methodu çağırılmaktadır.
     * @param id
     * @param requestHeader
     * @return ResponseEntity ile response dönmektedir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable long id,@RequestHeader(value="Accept") String requestHeader){
        CharacterDTO characterDTO = characterService.getCharacter(id);
        HttpHeaders responseHeader = new HttpHeaders();
        reportService.saveLog("/character/"+id,
                requestHeader,"",
                responseHeader.toString(),characterDTO);

        return new ResponseEntity<CharacterDTO>(characterDTO,responseHeader, HttpStatus.OK);
    }
}
