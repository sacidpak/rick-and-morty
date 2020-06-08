package com.rickandmorty.data.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD işlemleri için kullanılır.
 *
 * @author sacidpak
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character,Long> {
}
