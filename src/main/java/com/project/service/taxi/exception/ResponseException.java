package com.project.service.taxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


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
