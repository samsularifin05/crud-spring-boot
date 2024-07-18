package com.demoapi.hepers;

import java.util.Optional;

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

    public <T> ResponseEntity<ResponseData<T>> handleSuccess(Optional<String> message, Optional<T> payload) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(true);
        message.ifPresent(msg -> responseData.getMessage().add(msg));
        payload.ifPresent(responseData::setPayload);
        return ResponseEntity.ok(responseData);
    }
}
