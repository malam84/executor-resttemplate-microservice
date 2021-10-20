package com.demo.customer.info.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse
{
    public ErrorResponse(String message, HttpStatus statusCode, List<String> details) {
        super();
        this.message = message;
        this.statusCode = statusCode.value();
        this.details = details;
    }
  
    private String message;
    private int statusCode;
    private List<String> details;
}