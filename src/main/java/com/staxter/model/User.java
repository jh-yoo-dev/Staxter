package com.staxter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    
    @Column(length=12)
    private String firstName;
    
    @Column(length=12)
    private String lastName;
    
    @Column(length=12)
    private String userName;

    @Column(length=12)
    private String plainTextPassword;

    @Column(length=60)
    private String hashedPassword;
 
    @Builder
    public User(String id, String firstName, String lastName, String userName, String plainTextPassword, String hashedPassword){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.plainTextPassword = plainTextPassword;
        this.hashedPassword = hashedPassword;
    }
}