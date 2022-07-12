package com.loadbalancer;

public class Request {

    private String message;

    public Request(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
