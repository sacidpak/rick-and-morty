package com.rickandmorty.data.character;

import lombok.Getter;
import lombok.Setter;

/**
 * İlişkili olan tablolarda alt nesnelirin tüm datalarını göstermemek ve infinity loop
 * problemini çözmek için kullanılan class.
 *
 * @author sacidpak
 */
@Getter
@Setter
public class CharacterInfo {
    private long id;
    private String name;
}
