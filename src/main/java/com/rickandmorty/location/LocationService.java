package com.rickandmorty.location;

import com.rickandmorty.data.location.Location;
import com.rickandmorty.data.location.LocationDTO;
import com.rickandmorty.data.location.LocationMapper;
import com.rickandmorty.data.location.LocationRepository;
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
 * Location neslerinin business işlemlerinin yapıldığı servis classı.
 * LocationRepository ile işlemlerin yapıldığı dataların işlendiği yerdir.
 *
 * @version 1.0
 * @author sacidpak
 */
@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    @Value("${server.port}")
    private String port;

    /**
     * Id numarasına göre LocationRepository den dataların çekildiği daha
     * sonra Location entity sınıfının CharacterDTO ya mapping edildiği method.
     * @param id
     * @return LocationMapper ile mapping edilmiş Location nesnesi
     */
    public LocationDTO getLocation (long id){
        Location location = locationRepository.findById(id).orElse(null);
        return LocationMapper.INSTANCE.to(location);
    }

    /**
     * Tüm Location nesnelerinin CharacterRepository ile çekildiği daha sonra
     * Paging edilmiş dataların toplam sayfa sayısı, data sayılarının vb. CustomResponse sınıfının
     * PaginInfo nesnesi set edilildiği ardından Paging edilmiş dataların
     * LocationDTO nesnesine mapping işleminin yapıldığı method.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return CustomResponse<LocationDTO> locationCustomResponse
     */
    public CustomResponse<LocationDTO> getAllLocation(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Location> pagedResult = locationRepository.findAll(paging);

        CustomResponse<LocationDTO> locationCustomResponse = new CustomResponse<>();
        PagingInfo info = new PagingInfo();

        if (pagedResult.hasContent()){
            info.setCount(pagedResult.getTotalElements());
            info.setPage(pagedResult.getTotalPages());
            int pageNumber = pagedResult.getNumber() + 1;
            if(pageNumber < pagedResult.getTotalPages()) {
                info.setNextUrl(
                        "http://localhost:" + port + "/rickandmorty/location?pageNo=" + (pageNumber + 1)
                );
            }else{
                info.setNextUrl("");
            }
            if(pageNumber > 1) {
                info.setPrevUrl(
                        "http://localhost:" + port + "/rickandmorty/location?pageNo=" + (pageNumber - 1)
                );
            }else{
                info.setPrevUrl("");
            }

            List<LocationDTO> locationDTOList = new ArrayList<>();
            pagedResult.forEach(location -> {
                locationDTOList.add(LocationMapper.INSTANCE.to(location));
            });

            locationCustomResponse.setInfo(info);
            locationCustomResponse.setResult(locationDTOList);

        }else{
            info.setCount(0);
            info.setPage(0);
            info.setPrevUrl("");
            info.setNextUrl("");
            locationCustomResponse.setInfo(info);
        }

        return locationCustomResponse;
    }
}
