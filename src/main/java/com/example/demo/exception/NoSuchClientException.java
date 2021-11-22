package com.example.demo.exception;

public class NoSuchClientException extends RuntimeException{

    public NoSuchClientException() {
        super("Нет такого клиента");
    }
}
