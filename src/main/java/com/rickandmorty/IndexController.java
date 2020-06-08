package com.rickandmorty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sacidpak
 */
@RestController
@RequiredArgsConstructor
public class IndexController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public ResponseEntity<ApiIndex> welcomeIndex() {
        ApiIndex apiIndex = new ApiIndex(port);
        return new ResponseEntity<ApiIndex>(apiIndex,new HttpHeaders(), HttpStatus.OK);
    }
}

@Getter
@Setter
class ApiIndex{
    ApiIndex(String port){
       this.charactersUrl = "http://localhost:" + port + "/rickandmorty/character";
       this.episodesUrl = "http://localhost:" + port + "/rickandmorty/episode";
       this.locationsUrl = "http://localhost:" + port + "/rickandmorty/location";
       this.reportUrl = "http://localhost:" + port + "/rickandmorty/report";
    }
    String charactersUrl;
    String episodesUrl;
    String locationsUrl;
    String reportUrl;
}


