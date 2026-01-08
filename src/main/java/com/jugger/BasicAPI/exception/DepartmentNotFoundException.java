package com.jugger.BasicAPI.exception;

// import org.springframework.web.bind.annotation.ExceptionHandler;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String s) {
        super(s);
    }
}