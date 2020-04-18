package com.staxter.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.staxter.common.Message;
import com.staxter.entity.UserDto;
import com.staxter.model.User;
import com.staxter.userrepository.UserRepository;
import com.staxter.util.EncryptHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EncryptHelper encryptHelper;

    @Transactional
    public ResponseEntity<Map<String,Object>> createUser(UserDto userDto){
        Map<String,Object> resultMap = new HashMap<>();
        // encrypt password
        userDto.setHashedPassword(encryptHelper.hashPassword(userDto.getPassword()));
        
        if(userRepository.countByUserName(userDto.getUserName())>0){
            resultMap.put("code",Message.USER_ALREADY_EXISTS.getCode());
            resultMap.put("description",Message.USER_ALREADY_EXISTS.getDescription());
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.CONFLICT);
        }else {
            User user = userRepository.save(userDto.toEntity());
            resultMap.put("id",user.getId());
            resultMap.put("firstName",user.getFirstName());
            resultMap.put("lastName",user.getLastName());
            resultMap.put("userName",user.getUserName());
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
        }            
    }

    @Transactional
    public ResponseEntity<Map<String,Object>> login(UserDto userDto){
        Map<String,Object> resultMap = new HashMap<>();
        // encrypt password
        userDto.setHashedPassword(encryptHelper.hashPassword(userDto.getPassword()));
        if(!userRepository.login(userDto.getUserName(), userDto.getPassword()).isPresent()){
            resultMap.put("code",Message.LOGIN_FAILED.getCode());
            resultMap.put("description",Message.LOGIN_FAILED.getDescription());
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.NOT_ACCEPTABLE);
        }else {
            resultMap.put("code",Message.LOGIN_SUCCESS.getCode());
            resultMap.put("description",Message.LOGIN_SUCCESS.getDescription());
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.ACCEPTED);
        }

    }
}