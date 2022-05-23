package com.letscode.ecusuarioapi.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {

    private String userName;
    private String password;
    private String name;
    private String email;
    private String cartId;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;

    public UserEntity toEntity() {
        return new UserEntity(
                this.getUserName(),
                this.getPassword(),
                this.getName(),
                this.getEmail(),
                this.getCartId(),
                this.getDateCreated(),
                this.getDateUpdated()
        );
    }

    public UserRequest(String userName, String password, String name, String email) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.cartId = "0";
        this.dateCreated = ZonedDateTime.now();
        this.dateUpdated = ZonedDateTime.now();
    }
}


