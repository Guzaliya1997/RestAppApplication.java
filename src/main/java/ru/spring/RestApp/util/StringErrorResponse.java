package ru.spring.RestApp.util;

public class StringErrorResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StringErrorResponse(String message) {
        this.message = message;
    }
}
