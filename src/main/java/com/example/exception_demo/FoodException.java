package com.example.exception_demo;

public class FoodException extends RuntimeException{

    private Integer code;

    public FoodException( Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
