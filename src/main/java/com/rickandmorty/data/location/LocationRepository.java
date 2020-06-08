package com.rickandmorty.data.location;

import com.rickandmorty.data.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD işlemleri için kullanılır.
 *
 * @author sacidpak
 */
@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
