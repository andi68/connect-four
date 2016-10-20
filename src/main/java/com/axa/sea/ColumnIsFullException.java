package com.axa.sea;

public class ColumnIsFullException extends RuntimeException{

    public ColumnIsFullException() {
    }

    public ColumnIsFullException(String message) {
        super(message);
    }
}
