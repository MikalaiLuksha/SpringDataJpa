package tms.springdatajpa.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tms.springdatajpa.entity.ApiResponse;

import javax.el.MethodNotFoundException;

@ControllerAdvice
public class NewExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler (MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ApiResponse> NewMethodArgumentTypeMismatchException (){
        return new ResponseEntity<>(new ApiResponse (HttpStatus.BAD_REQUEST.value(), "pet", "Invalid ID supplied"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (MethodNotFoundException .class)
    public final ResponseEntity<ApiResponse> NewMethodNotFoundException (MethodNotFoundException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ApiResponse (HttpStatus.METHOD_NOT_ALLOWED.value(), "pet", message), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler (NullPointerException .class)
    public final ResponseEntity<ApiResponse> NewNullPointerException (NullPointerException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ApiResponse (HttpStatus.NOT_FOUND.value(), "pet", message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (IllegalArgumentException.class)
    public final ResponseEntity<ApiResponse> NewIllegalArgumentException (IllegalArgumentException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(new ApiResponse (HttpStatus.BAD_REQUEST.value(), "pet", message), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiResponse (HttpStatus.BAD_REQUEST.value(), "pet", "Invalid Order"), HttpStatus.BAD_REQUEST);
    }


}
