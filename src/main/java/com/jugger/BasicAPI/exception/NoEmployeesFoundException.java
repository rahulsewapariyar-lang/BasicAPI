package com.jugger.BasicAPI.exception;

public class NoEmployeesFoundException extends RuntimeException {
    public NoEmployeesFoundException(String s) {
        super(s);
    }
}
