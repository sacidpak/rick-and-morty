package com.rickandmorty.data.location;

import com.rickandmorty.data.BaseEntityInfo;
import com.rickandmorty.data.character.CharacterInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Gerçek Entity classının map işlemi yapıdığı class.
 *
 * @author sacidpak
 */
@Getter
@Setter
public class LocationDTO extends BaseEntityInfo {
    private String type;
    private String dimension;
    private Set<CharacterInfo> nativeBorn = new HashSet<>();
    private Set<CharacterInfo> residents = new HashSet<>();
}
