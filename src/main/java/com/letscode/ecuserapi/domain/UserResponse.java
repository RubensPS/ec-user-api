package com.letscode.ecuserapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String userName;
    private String name;
    private String email;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;

    public UserResponse(UserEntity entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.dateCreated = entity.getDateCreated();
        this.dateUpdated = entity.getDateUpdated();
    }

}
