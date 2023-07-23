package com.gamedoora.backend.studioservices.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1")
@CrossOrigin("*")
public class BaseController {
    public <T> ResponseEntity<T> createResponse(T entity , HttpStatus httpStatus){
        return entity == null ? new ResponseEntity<>(httpStatus) : new ResponseEntity<>(entity , httpStatus);
    }
}
