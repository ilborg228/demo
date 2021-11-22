package com.example.demo.exception;

public class NoSuchCreditException extends RuntimeException{

    public NoSuchCreditException() {
        super("Нет такого кредита");
    }
}
