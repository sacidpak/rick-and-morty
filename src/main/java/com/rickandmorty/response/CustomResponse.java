package com.rickandmorty.response;

import lombok.Data;

import java.util.List;

/**
 * @author sacidpak
 */
@Data
public class CustomResponse<T> {
    private PagingInfo info;
    private List<T> result;
}
