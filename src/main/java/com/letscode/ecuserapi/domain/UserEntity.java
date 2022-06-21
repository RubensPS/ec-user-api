package com.letscode.ecuserapi.domain;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String email;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;

    public UserEntity(String userName, String password, String name, String email, ZonedDateTime dateCreated, ZonedDateTime dateUpdated) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

}
