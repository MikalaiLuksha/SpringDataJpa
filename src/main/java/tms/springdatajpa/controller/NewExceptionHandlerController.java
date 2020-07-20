package tms.springdatajpa.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tms.springdatajpa.entity.ApiResponse;

import javax.el.MethodNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class NewExceptionHandlerController extends ResponseEntityExceptionHandler{


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.METHOD_NOT_ALLOWED);
    }

        @ExceptionHandler (MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<Object> NewMethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException ex, WebRequest request){
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
            apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                    ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
            apiError.setDebugMessage(ex.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
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
