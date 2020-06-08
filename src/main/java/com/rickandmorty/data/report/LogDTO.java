package com.rickandmorty.data.report;

import com.rickandmorty.data.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Gerçek Entity classının map işlemi yapıdığı class.
 *
 * @author sacidpak
 */
@Getter
@Setter
public class LogDTO {
    private String name;
    private String requestTime;
    private String requestHeader;
    private String requestBody;
    private String responseHeader;
    private String responseBody;
}
