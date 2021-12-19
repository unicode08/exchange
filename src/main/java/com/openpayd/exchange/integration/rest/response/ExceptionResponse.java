package com.openpayd.exchange.integration.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionResponse {
    private String exceptionMessage;
    private HttpStatus httpStatus;
}
