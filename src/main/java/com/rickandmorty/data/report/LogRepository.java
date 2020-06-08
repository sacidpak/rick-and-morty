package com.rickandmorty.data.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CRUD işlemleri için kullanılır.
 *
 * @author sacidpak
 */
@Repository
public interface LogRepository extends JpaRepository<Log,Long> {
}
