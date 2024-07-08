package com.example.mongodbtests.exception;

import lombok.experimental.StandardException;

@StandardException
public class MyException extends Exception{

    public MyException(String message){
        super(message,null,true,false);
    }
}
