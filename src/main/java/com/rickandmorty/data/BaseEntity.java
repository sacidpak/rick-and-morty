package com.rickandmorty.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.util.Date;

/**
 * Tüm entitylerde bulunan alanların extends edilmesi için oluşturulmuş class.
 *
 * @author sacidpak
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", allocationSize = 1)
    @Column(name = "id")
    protected Long id;

    @Column(name = "name")
    private String name;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Date createdDate = new Date();
}
