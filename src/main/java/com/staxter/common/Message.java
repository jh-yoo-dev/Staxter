package com.staxter.common;

public enum Message {
    SUCCESS("SUCCESS","Request Success"),
    LOGIN_SUCCESS("LOGIN_SUCCESS","Login Success"),
    CREATE_SUCCESS("CREATE_SUCCESS","Create Success"),
    
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "A user with the given username already exists"),
    LOGIN_FAILED("LOGIN_FAILED","Login Failed"),
    FAIL("FAIL","Request Fail");

    private String code;
    private String description;

    Message(String code, String description){
        this.code = code;
        this.description = description;
    }

    public String getCode(){
        return this.code;
    }

    public String getDescription(){
        return this.description;
    }
}