package com.shapran.exception;

public class UserInputException extends Exception{
    public UserInputException(){
    }
    public UserInputException(final String message){
        super(message);
    }
}
