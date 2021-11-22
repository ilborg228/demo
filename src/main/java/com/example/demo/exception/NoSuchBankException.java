package com.example.demo.exception;

public class NoSuchBankException extends RuntimeException{

    public NoSuchBankException() {
        super("Нет такого банка");
    }
}
