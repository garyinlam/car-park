package com.nology.carpark.exceptions;

public class NoSpaceException extends RuntimeException{
    public NoSpaceException(String message) {
        super(message);
    }
}
