package com.rickandmorty.data.episode;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Entity nesnelerinin mapping işlemlerini yapmak için kullanılan methodları bulundurur.
 *
 * @author sacidpak
 */
@Mapper
public interface EpisodeMapper {
    EpisodeMapper INSTANCE = Mappers.getMapper(EpisodeMapper.class);
    EpisodeDTO to (Episode episode);
    Episode from (EpisodeDTO episodeDTO);
}
