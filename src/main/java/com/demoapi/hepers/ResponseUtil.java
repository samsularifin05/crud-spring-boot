package com.demoapi.hepers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.demoapi.dto.ResponseData;

@Component
public class ResponseUtil {

    public <T> ResponseEntity<ResponseData<T>> handleErrors(Errors errors) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(false);
        for (ObjectError error : errors.getAllErrors()) {
            responseData.getMessage().add(error.getDefaultMessage());
        }
        responseData.setPayload(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    public <T> ResponseEntity<ResponseData<T>> handleCustomError(String errorMessage) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(false);
        responseData.getMessage().add(errorMessage);
        responseData.setPayload(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    public <T> ResponseEntity<ResponseData<T>> handleSuccess(T payload) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(payload);
        return ResponseEntity.ok(responseData);
    }
}
