package com.base.apiserver.error;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.base.apiserver.error.excetion.InvalidException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class ApiExceptionHandler {
	
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidException.class)
    @ResponseBody
    public ErrorResult handleInvalid(HttpServletRequest req, InvalidException ex) {
        return new ErrorResult(ApiErrorCode.INVALID_PARAMETER_ERROR_CODE, ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, req.getRequestURL().toString());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorResult handleNullRequestBody(HttpServletRequest req, Exception ex) {
        return new ErrorResult(ApiErrorCode.REQUEST_BODY_NULL, "Required request body is null", HttpStatus.BAD_REQUEST, req.getRequestURL().toString());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ErrorResult handleArgumentTypeMismatch(HttpServletRequest req, Exception ex) {
        return new ErrorResult(ApiErrorCode.ARGUMENT_TYPE_MISMATCH_ERROR_CODE, "Argument type mismatch", HttpStatus.BAD_REQUEST, req.getRequestURL().toString());
    }
    
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorResult handleNotJsonRequest(HttpServletRequest req, HttpMediaTypeNotAcceptableException ex) {
        return new ErrorResult(ApiErrorCode.NOT_JSON_MEDIA_TYPE_ERROR_CODE, "Only support Content type 'application/json'", HttpStatus.BAD_REQUEST, req.getRequestURL().toString());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({IOException.class, JsonProcessingException.class, JsonParseException.class, JsonMappingException.class})
    public ErrorResult handleJsonProcessing(HttpServletRequest req, Exception ex) {
        return new ErrorResult(ApiErrorCode.JSON_PROCESSING_ERROR_CODE, ex.getMessage(), HttpStatus.BAD_REQUEST, req.getRequestURL().toString());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorResult handleExcetion(HttpServletRequest req, Exception ex) {
        return new ErrorResult(ApiErrorCode.INTERNAL_ERROR_CODE, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, req.getRequestURL().toString());
    }
}
