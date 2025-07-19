package com.example.DataCollectionService.exception;


import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppIdErrorException.class)
    public ResponseEntity<Map<String,String>> errorHandle(AppIdErrorException ex){
        Map<String,String> mp = new HashMap<>();
        mp.put("Error Message",ex.getMessage());
        return  new ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoPlanFoundException.class)
    public ResponseEntity<Map<String,String>> errorHandle(NoPlanFoundException ex){
        Map<String,String> mp = new HashMap<>();
        mp.put("Error Message",ex.getMessage());
        return  new ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CaseNumberNotFoundException.class)
    public ResponseEntity<Map<String,String>> errorHandle(CaseNumberNotFoundException ex){
        Map<String,String> mp = new HashMap<>();
        mp.put("Error Message",ex.getMessage());
        return  new ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateCaseNumberException.class)
    public ResponseEntity<Map<String,String>> errorHandle(DuplicateCaseNumberException ex){
        Map<String,String> mp = new HashMap<>();
        mp.put("Error Message",ex.getMessage());
        return  new ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
    }

}
