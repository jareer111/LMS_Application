package com.jareer.lms.app.handlers;

import com.jareer.lms.app.dtos.AppErrorDTO;
import com.jareer.lms.app.exceptions.ItemNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppErrorDTO> handleUnknownExceptions(Exception e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
                new AppErrorDTO(
                        request.getRequestURI(),
                        e.getMessage(),
                        null,
                        400
                )
        );
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<AppErrorDTO> handleItemNotFoundException(ItemNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(new AppErrorDTO(request.getRequestURI(), e.getMessage(), 404));
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<AppErrorDTO> sqlException(DataIntegrityViolationException e, HttpServletRequest request) {
        return ResponseEntity.status(409)
                .body(new AppErrorDTO(request.getRequestURI(), "Duplicate entry found !", 409));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AppErrorDTO> AccessDeniedExceptionHandler(AccessDeniedException e, HttpServletRequest request) {
        return ResponseEntity.status(403)
                .body(new AppErrorDTO(request.getRequestURI(), "Forbidden .", 403));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<AppErrorDTO> ExpiredJwtExceptionHandler(ExpiredJwtException e, HttpServletRequest request) {
        return ResponseEntity.status(401)
                .body(new AppErrorDTO(request.getRequestURI(), "Token has expired .", 401));
    }


}
