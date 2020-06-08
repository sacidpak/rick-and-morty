package com.rickandmorty.data.episode;

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
public class EpisodeDTO extends BaseEntityInfo {
    private String airDate;
    private String episodeCode;
    private Set<CharacterInfo> characters = new HashSet<>();
}
