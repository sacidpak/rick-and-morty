package com.rickandmorty.data.character;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Entity nesnelerinin mapping işlemlerini yapmak için kullanılan methodları bulundurur.
 *
 * @author sacidpak
 */
@Mapper
public interface CharacterMapper {
    CharacterMapper INSTANCE = Mappers.getMapper(CharacterMapper.class);
    CharacterDTO to (Character character);
    Character from (CharacterDTO characterDTO);
}