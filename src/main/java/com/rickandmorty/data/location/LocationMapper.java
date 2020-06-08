package com.rickandmorty.data.location;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Entity nesnelerinin mapping işlemlerini yapmak için kullanılan methodları bulundurur.
 *
 * @author sacidpak
 */
@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);
    LocationDTO to (Location location);
    Location from (LocationDTO locationDTO);
}
