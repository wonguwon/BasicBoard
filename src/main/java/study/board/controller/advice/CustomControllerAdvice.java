package study.board.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.board.util.CustomJWTException;

import java.util.Map;

@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(CustomJWTException.class)
    protected ResponseEntity<?> handleJWTException(CustomJWTException e) {
        String msg = e.getMessage();
        return ResponseEntity.ok().body(Map.of("error", msg));
    }
}
