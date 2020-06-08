package com.rickandmorty.data.location;

import com.rickandmorty.data.BaseEntity;
import com.rickandmorty.data.character.Character;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * tbl_location tablosunun oluşturulduğu entity classı.
 *
 * @author sacidpak
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_location")
public class Location extends BaseEntity {
    @Column(name = "type")
    private String type;

    @Column(name = "dimension")
    private String dimension;

    @OneToMany(mappedBy = "origin", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Character> nativeBorn;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Character> residents;

}
