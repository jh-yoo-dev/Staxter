package com.staxter.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class EncryptHelper {

    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public boolean checkPass(String plainTextPassword, String hasedPassword){
        if(BCrypt.checkpw(plainTextPassword,hasedPassword)) {
            // dosomething();
            return true;
        }
        return true;
    }

}