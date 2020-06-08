package com.rickandmorty.response;

import lombok.*;

/**
 * @author sacidpak
 */
@Data
public class PagingInfo {
    private long count;
    private Integer page;
    private String nextUrl;
    private String prevUrl;
}
