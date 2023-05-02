package com.spring.restWebServiceAPI.handler;

import com.spring.restWebServiceAPI.error.ErrorType;
import com.spring.restWebServiceAPI.exception.InvoiceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class InvoiceErrorHandler {

    /**
     * In case of InvoiceNotFoundException is thrown
     * from any controller method, this logic gets
     * executed which behaves like re-usable and
     * clear code (Code Modularity)
     * @param nfe
     * @return ResponseEntity
     */
    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ErrorType> handleNotFound(InvoiceNotFoundException nfe){
        return new ResponseEntity<ErrorType>(
                new ErrorType(
                        new Date(System.currentTimeMillis()).toString(),
                        "404- NOT FOUND",
                        nfe.getMessage()),
                        HttpStatus.NOT_FOUND
                );
    }
}
