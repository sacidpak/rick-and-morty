package com.rickandmorty.data.episode;

import com.rickandmorty.data.episode.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD işlemleri için kullanılır.
 *
 * @author sacidpak
 */
@Repository
public interface EpisodeRepository extends JpaRepository<Episode,Long> {
}
