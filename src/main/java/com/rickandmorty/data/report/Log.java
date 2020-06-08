package com.rickandmorty.data.report;

import com.rickandmorty.data.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * tbl_log tablosunun oluşturulduğu entity classı.
 *
 * @author sacidpak
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_log")
public class Log extends BaseEntity {
    @Column(name = "requset_time")
    private String requestTime;

    @Column(name = "requset_header")
    private String requestHeader;

    @Column(name = "requset_body")
    private String requestBody;

    @Column(name = "response_header")
    private String responseHeader;

    @Column(name = "response_body")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String responseBody;

}
