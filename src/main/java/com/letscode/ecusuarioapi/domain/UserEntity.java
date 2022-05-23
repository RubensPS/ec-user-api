package com.letscode.ecusuarioapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private String name;
    private String email;
    private String cartId;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;

    public UserEntity(String userName, String password, String name, String email, String cartId, ZonedDateTime dateCreated, ZonedDateTime dateUpdated) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.cartId = cartId;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

}
