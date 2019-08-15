package com.qzy.mab.exception;

public class AuthenticateTokenInvalidException extends Exception{

    public AuthenticateTokenInvalidException(String message){
        super(message);
    }


    public AuthenticateTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
