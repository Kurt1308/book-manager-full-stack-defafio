package com.bookmanager.backend.config.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Erros de validação (@Valid)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ) {


        Map<String, String> errors =
                new HashMap<>();


        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);

    }



    /**
     * Recurso não encontrado
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException exception
    ) {


        Map<String, Object> response =
                new HashMap<>();


        response.put(
                "timestamp",
                LocalDateTime.now()
        );


        response.put(
                "status",
                HttpStatus.NOT_FOUND.value()
        );


        response.put(
                "error",
                "Not Found"
        );


        response.put(
                "message",
                exception.getMessage()
        );


        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

    }





    /**
     * Recurso duplicado
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(
            DuplicateResourceException exception
    ) {


        Map<String, Object> response =
                new HashMap<>();


        response.put(
                "timestamp",
                LocalDateTime.now()
        );


        response.put(
                "status",
                HttpStatus.CONFLICT.value()
        );


        response.put(
                "error",
                "Conflict"
        );


        response.put(
                "message",
                exception.getMessage()
        );


        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);

    }





    /**
     * Erro de autenticação
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentials(
            BadCredentialsException exception
    ) {


        Map<String, Object> response =
                new HashMap<>();


        response.put(
                "timestamp",
                LocalDateTime.now()
        );


        response.put(
                "status",
                HttpStatus.UNAUTHORIZED.value()
        );


        response.put(
                "error",
                "Unauthorized"
        );


        response.put(
                "message",
                exception.getMessage()
        );


        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);

    }





    /**
     * Demais erros
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(
            Exception exception
    ) {


        Map<String, Object> response =
                new HashMap<>();


        response.put(
                "timestamp",
                LocalDateTime.now()
        );


        response.put(
                "status",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );


        response.put(
                "error",
                "Internal Server Error"
        );


        response.put(
                "message",
                exception.getMessage()
        );


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);

    }

}