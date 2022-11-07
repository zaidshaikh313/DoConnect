package com.greatlearning.exceptions;

public class InvalidUserCredentialsException  extends RuntimeException{
    public  InvalidUserCredentialsException(String s){
        super(s);
    }
}
