package com.rickandmorty.location;

import com.rickandmorty.data.location.LocationDTO;
import com.rickandmorty.report.ReportService;
import com.rickandmorty.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Location sayfalarının mapping işlemi yapılıdığı yönlendirici class.
 *
 * @version 1.0
 * @author sacidpak
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;
    private final ReportService reportService;

    /**
     * Tüm location listesinin gösterildiği method.Paging işlemi yapılmaktadır.
     * Ayrıca report çıktısı için saveLog methodu çağırılmaktadır.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @param requestHeader
     * @return ResponseEntity ile response vermektedir.
     */
    @GetMapping
    public ResponseEntity<CustomResponse<LocationDTO>> getAllLocation(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestHeader(value="Accept") String requestHeader)
    {
        CustomResponse<LocationDTO> customResponse = locationService.getAllLocation(pageNo-1, pageSize, sortBy);
        HttpHeaders responseHeader = new HttpHeaders();
        reportService.saveLog("/location?page="+pageNo,
                requestHeader,"",
                responseHeader.toString(),customResponse);
        return new ResponseEntity<CustomResponse<LocationDTO>>(customResponse, responseHeader, HttpStatus.OK);
    }

    /**
     * Id numarası ile tek bir location getiren method.
     * Ayrıca report çıktısı için saveLog methodu çağırılmaktadır.
     * @param id
     * @param requestHeader
     * @return ResponseEntity ile response dönmektedir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable long id,@RequestHeader(value="Accept") String requestHeader){
        LocationDTO locationDTO = locationService.getLocation(id);
        HttpHeaders responseHeader = new HttpHeaders();
        reportService.saveLog("/location/"+id,
                requestHeader,"",
                responseHeader.toString(),locationDTO);
        return new ResponseEntity<LocationDTO>(locationDTO,responseHeader, HttpStatus.OK);
    }
}
