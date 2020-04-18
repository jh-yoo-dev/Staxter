package com.staxter.entity;

import javax.validation.constraints.NotBlank;

import com.staxter.model.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private String id;

    private String firstName;

    private String lastName;

    @NotBlank(message = "Required Field")
    private String userName;

    @NotBlank(message = "Required Field")
    private String password;

    private String hashedPassword;

    public User toEntity(){
        return User.builder()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .userName(userName)
        .plainTextPassword(password)
        .hashedPassword(hashedPassword)
        .build();
    }

    @Builder
    public UserDto(String id, String firstName, String lastName, String userName, String password, String hashedPassword){
        this.id= id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.hashedPassword = hashedPassword;
    }

   
}