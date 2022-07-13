package com.example.demo.exception;

import lombok.Data;

@Data
public class FieldNotFoundException extends RuntimeException{
    private String fieldName;

    public FieldNotFoundException(String fieldName) {
        super(String.format("field not found with : %s", fieldName));
        this.fieldName = fieldName;
    }
}
