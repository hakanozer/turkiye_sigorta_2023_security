package com.works.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid( MethodArgumentNotValidException ex ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", false);
        hm.put("errors", parseError(ex.getFieldErrors()) );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    private List parseError(List<FieldError> errors) {
        List ls = new ArrayList();
        for( FieldError item : errors ) {
            String field = item.getField();
            String message = item.getDefaultMessage();
            Map<String, String> err = new HashMap<>();
            err.put("field", field);
            err.put("message", message);
            ls.add(err);
        }
        return ls;
    }


}
