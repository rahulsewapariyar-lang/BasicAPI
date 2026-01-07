package com.jugger.BasicAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value={EmployeeNotFoundException.class})
    public ResponseEntity<ErrorResponse>handleEmployeeNotFoundException(EmployeeNotFoundException ex){
        //create payload
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(404)
                .error(ex.getMessage())
                .build();
        //return the payload
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value={EmployeeNullException.class})
    public ResponseEntity<ErrorResponse>employeeNullException(EmployeeNullException ex){
        //create payload
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(400)
                .error(ex.getMessage())
                .build();
        //return the payload
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={DuplicateEmailException.class})
    public ResponseEntity<ErrorResponse>duplicateEmailException(DuplicateEmailException ex){
        //create payload
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(400)
                .error(ex.getMessage())
                .build();
        //return the payload
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={NoEmployeesFoundException.class})
    public ResponseEntity<ErrorResponse>emptyList(NoEmployeesFoundException ex){
        //create payload
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(404)
                .error(ex.getMessage())
                .build();
        //return the payload
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value={DepartmentNotFoundException.class})
    public ResponseEntity<ErrorResponse>departmentNotFound(DepartmentNotFoundException ex){
        //create payload
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(404)
                .error(ex.getMessage())
                .build();
        //return the payload
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
