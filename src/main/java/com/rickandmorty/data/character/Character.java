package com.rickandmorty.data.character;

import com.rickandmorty.data.BaseEntity;
import com.rickandmorty.data.episode.Episode;
import com.rickandmorty.data.location.Location;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * tbl_character tablosunun oluşturulduğu entity classı.
 *
 * @author sacidpak
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_character")
public class Character extends BaseEntity {
    @Column(name = "status")
    private String status;

    @Column(name = "species")
    private String species;

    @Column(name = "type")
    private String type;

    @Column(name = "gender")
    private String gender;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name="origin_id")
    private Location origin;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToMany
    @JoinTable(name="tbl_char_episode",
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"character_id", "episode_id"})
            },
            joinColumns = { @JoinColumn(name = "character_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "episode_id", referencedColumnName = "id") }
            )
    private Set<Episode> episode = new HashSet<>();


}
