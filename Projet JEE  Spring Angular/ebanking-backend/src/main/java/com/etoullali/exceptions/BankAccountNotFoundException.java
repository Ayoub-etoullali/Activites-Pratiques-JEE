package com.etoullali.exceptions;

public class BankAccountNotFoundException extends Exception{
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
