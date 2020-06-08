package com.rickandmorty.data;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * DTO classlarına extend etmek için oluşturulmuş class.
 *
 * @author sacidpak
 */
@Getter
@Setter
public class BaseEntityInfo {
    private long id;
    private String name;
    private Date createdDate;
}
