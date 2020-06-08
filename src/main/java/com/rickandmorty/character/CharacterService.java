package com.rickandmorty.character;

import com.rickandmorty.data.character.Character;
import com.rickandmorty.data.character.CharacterDTO;
import com.rickandmorty.data.character.CharacterMapper;
import com.rickandmorty.data.character.CharacterRepository;
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
 * Character neslerinin business işlemlerinin yapıldığı servis classı.
 * CharacterRepository ile işlemlerin yapıldığı dataların işlendiği yerdir.
 *
 * @version 1.0
 * @author sacidpak
 */
@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    @Value("${server.port}")
    private String port;

    /**
     * Id numarasına göre CharacterRepository den dataların çekildiği daha
     * sonra Character entity sınıfının CharacterDTO ya mapping edildiği method.
     * @param id
     * @return CharacterMapper ile mapping edilmiş Character nesnesi
     */
    public CharacterDTO getCharacter (long id){
        Character character = characterRepository.findById(id).orElse(null);
        return CharacterMapper.INSTANCE.to(character);
    }

    /**
     * Tüm Character nesnelerinin CharacterRepository ile çekildiği daha sonra
     * Paging edilmiş dataların toplam sayfa sayısı, data sayılarının vb. CustomResponse sınıfının
     * PaginInfo nesnesi set edilildiği ardından Paging edilmiş dataların
     * CharacterDTO nesnesine mapping işleminin yapıldığı method.
     * @param pageNo
     * @param pageSize
     * @param sortBy
     * @return CustomResponse<CharacterDTO> characterCustomResponse
     */
    public CustomResponse<CharacterDTO> getAllCharacter(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Character> pagedResult = characterRepository.findAll(paging);

        CustomResponse<CharacterDTO> characterCustomResponse = new CustomResponse<>();
        PagingInfo info = new PagingInfo();

        if (pagedResult.hasContent()){
            info.setCount(pagedResult.getTotalElements());
            info.setPage(pagedResult.getTotalPages());
            int pageNumber = pagedResult.getNumber() + 1;
            if(pageNumber < pagedResult.getTotalPages()) {
                info.setNextUrl(
                        "http://localhost:" + port + "/rickandmorty/character?pageNo=" + (pageNumber + 1)
                );
            }else{
                info.setNextUrl("");
            }
            if(pageNumber > 1) {
                info.setPrevUrl(
                        "http://localhost:" + port + "/rickandmorty/character?pageNo=" + (pageNumber - 1)
                );
            }else{
                info.setPrevUrl("");
            }

            //Paping edilmiş nesnelerin mapping edilmesi
            List<CharacterDTO> characterDTOList = new ArrayList<>();
            pagedResult.forEach(character -> {
                characterDTOList.add(CharacterMapper.INSTANCE.to(character));
            });

            characterCustomResponse.setInfo(info);
            characterCustomResponse.setResult(characterDTOList);

        }else{
            info.setCount(0);
            info.setPage(0);
            info.setPrevUrl("");
            info.setNextUrl("");
            characterCustomResponse.setInfo(info);
        }

        return characterCustomResponse;
    }

}
