package com.staxter.restcontroller;

import java.util.Map;

import com.staxter.entity.UserDto;
import com.staxter.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/userservice")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value="/register")
    public ResponseEntity<Map<String,Object>> register(@RequestBody UserDto params){
        return userService.createUser(params);
    }

    @PostMapping(value="/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserDto params) {
        return userService.login(params);
    }
    
}