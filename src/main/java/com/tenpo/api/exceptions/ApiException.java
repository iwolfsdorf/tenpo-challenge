package com.tenpo.api.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiException extends Exception {

    private final HttpStatus httpStatus;

    public ApiException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}