package com.example.retrofitutil.exception;

public class CreateCallFailException extends Exception{
    public CreateCallFailException(){
        super("don't create retrofit call! please check your interface has get method?");
    }
}
