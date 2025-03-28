package com.company.expections.handler;

import com.company.expections.exp.AppBadRequestException;
import com.company.expections.exp.DuplicateItemException;
import com.company.expections.exp.ItemNotFoundException;
import com.company.expections.exp.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {


    @ExceptionHandler(AppBadRequestException.class)
    public ResponseEntity<?> handleAppBadRequestException(AppBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<?> handleAppBadRequestException(UnAuthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handleItemNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateItemException.class)
    public ResponseEntity<?> handleDuplicateItemException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Item Already Exist!");
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(MethodNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(HttpStatus.BAD_REQUEST);
    }
}
