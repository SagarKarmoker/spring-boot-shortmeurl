package com.sagar.shorttheurl.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response {
    private String message;
    private int statusCode;

    public Response(String shortUrl, HttpStatus httpStatus) {
        this.message = shortUrl;
        this.statusCode = httpStatus.value();
    }
}
