package com.ecom.app.orderservice.exception;

import org.springframework.http.HttpStatus;

public class OrderException extends Throwable {

    private HttpStatus httpStatus;

    public OrderException(String message, HttpStatus statusCode) {
        super(message);
        this.httpStatus = statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
