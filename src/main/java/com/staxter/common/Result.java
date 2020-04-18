package com.staxter.common;

/**
 * Result class
 * @author jhyoo
 * @param code
 * @param description
 */
public class Result {

    private String code;
    private String description; 
    private Object object;

    public Result(Message message){
        super();
        this.description = message.getDescription();
        this.code = message.getCode();
    }
    
    public Result(Message message,Object object){
        super();
        this.description = message.getDescription();
        this.code = message.getCode();
        this.object = object;
    }

    public Result(Object object){
        super();
        this.object = object;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCode() {
        return this.code;
    }

    public void setCode(String description) {
        this.description = description;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ResultMessage [code=" + code + ", message=" + description + ", object=" + object + "]";
    }

}