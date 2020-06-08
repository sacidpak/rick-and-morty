package com.rickandmorty.data.episode;

import com.rickandmorty.data.BaseEntity;
import com.rickandmorty.data.character.Character;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * tbl_episode tablosunun oluşturulduğu entity classı.
 *
 * @author sacidpak
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_episode")
public class Episode extends BaseEntity {
    @Column(name = "air_date")
    private String airDate;

    @Column(name = "episode_code")
    private String episodeCode;

    @ManyToMany(mappedBy = "episode")
    private Set<Character> characters = new HashSet<>();
}
