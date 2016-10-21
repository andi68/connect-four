package com.axa.sea;

public class GameOverException extends RuntimeException {
    public GameOverException() {
        super();
    }

    public GameOverException(String message) {
        super(message);
    }
}
