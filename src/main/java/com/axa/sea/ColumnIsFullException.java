package com.axa.sea;

public class ColumnIsFullException extends RuntimeException{

    public ColumnIsFullException() {
        super();
    }

    public ColumnIsFullException(String message) {
        super(message);
    }
}
