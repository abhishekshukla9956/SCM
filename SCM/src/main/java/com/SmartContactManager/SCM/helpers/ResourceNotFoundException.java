package com.SmartContactManager.SCM.helpers;

public class ResourceNotFoundException extends RuntimeException {
    

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    
    
}
}
