package com.qzy.mab.exception;

/**
 * Created by hidehai on 2015/9/15.
 */
public class AuthenticateTokenException extends Exception{

    public AuthenticateTokenException(String message){
        super(message);
    }


    public AuthenticateTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
