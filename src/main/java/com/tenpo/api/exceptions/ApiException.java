package com.tenpo.api.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {

    private static final long serialVersionUID = 2278100290639382168L;

    private final HttpStatus httpStatus;

    public ApiException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}