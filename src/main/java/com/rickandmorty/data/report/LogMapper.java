package com.rickandmorty.data.report;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Entity nesnelerinin mapping işlemlerini yapmak için kullanılan methodları bulundurur.
 *
 * @author sacidpak
 */
@Mapper
public interface LogMapper {
    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);
    LogDTO to (Log log);
    Log from (LogDTO logDTO);
}
