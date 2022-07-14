package com.example.demo.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    private String ResourceName;
    private String FieldName;
    private Long FieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : %d", resourceName, fieldName, fieldValue));
        this.ResourceName = resourceName;
        this.FieldName = fieldName;
        this.FieldValue = fieldValue;
    }
}
