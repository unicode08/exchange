package com.openpayd.exchange.integration.configuration;

import com.openpayd.exchange.domain.exception.ConvertAmountException;
import com.openpayd.exchange.domain.exception.ExchangeRateException;
import com.openpayd.exchange.integration.rest.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {ExchangeRateException.class, ConvertAmountException.class})
    public ResponseEntity<ExceptionResponse> handleDomain(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setExceptionMessage(ex.getMessage());
        exceptionResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> handleUnknownException(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setExceptionMessage(ex.getMessage());
        exceptionResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
