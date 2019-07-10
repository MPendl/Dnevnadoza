package com.dnevnadoza.network.models;

public class DoggoResponse {

    private String status;
    private String message;

    public DoggoResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
