package com.rickandmorty.data.character;

import com.rickandmorty.data.BaseEntityInfo;
import com.rickandmorty.data.episode.EpisodeInfo;
import com.rickandmorty.data.location.LocationInfo;
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
public class CharacterDTO extends BaseEntityInfo {
    private String status;
    private String species;
    private String type;
    private String gender;
    private LocationInfo origin;
    private LocationInfo location;
    private Set<EpisodeInfo> episode = new HashSet<>();
}
