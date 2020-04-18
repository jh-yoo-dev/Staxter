package com.staxter.userrepository;

import java.util.Optional;

import com.staxter.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String>{
    int countByUserName(String userName);
    
    @Query(value = "SELECT id from USER where USER_NAME = :userName and PLAIN_TEXT_PASSWORD = :password",
    nativeQuery = true)
   Optional<String> login(@Param("userName")String userName, @Param("password")String password);
}