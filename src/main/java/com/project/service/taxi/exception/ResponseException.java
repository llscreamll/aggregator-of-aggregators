package com.project.service.taxi.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class ResponseException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<AwesomeException> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new AwesomeException("Not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoginAndPasswordException.class)
    protected ResponseEntity<AwesomeException> handleLoginAndPasswordException() {
        return new ResponseEntity<>(new AwesomeException("Login or password not correct"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoginBeBusyException.class)
    protected ResponseEntity<AwesomeException> handleLoginBeBusyException() {
        return new ResponseEntity<>(new AwesomeException("Login be busy"), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(OrdersException.class)
    protected ResponseEntity<AwesomeException> handleOrdersException() {
        return new ResponseEntity<>(new AwesomeException("You already have an order, cancel it for a new one"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<AwesomeException> handleUserNotFoundException() {
        return new ResponseEntity<>(new AwesomeException("User not found"), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TaxiNotFoundException.class)
    protected ResponseEntity<AwesomeException> handleTaxiNotFoundException() {
        return new ResponseEntity<>(new AwesomeException("Taxi not found"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    protected ResponseEntity<AwesomeException> handleOrderNotFoundException() {
        return new ResponseEntity<>(new AwesomeException("Order not found"), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
        }

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                logger.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
            });
        }
        return new ResponseEntity<>(new AwesomeException(errorMap.toString()), HttpStatus.BAD_REQUEST);
    }



    private static class AwesomeException {
        private String message;
        public AwesomeException(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
