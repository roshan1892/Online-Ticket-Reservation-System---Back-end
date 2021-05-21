package com.project.trs;

import com.project.trs.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InvalidInputException.class)
  public ResponseEntity<Object> handleScheduleNotFoundException(
          Exception ex) {
    return this.handleErrorResponse(ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Object> handleAuthenticationException(
          Exception ex) {
    return this.handleErrorResponse(ex, HttpStatus.UNAUTHORIZED);
  }

  public ResponseEntity<Object> handleErrorResponse(Exception ex, HttpStatus status) {
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setStatus(status.value());
    exceptionResponse.setError(status.getReasonPhrase());
    exceptionResponse.setMessage(ex.getMessage());

    log.error(ex.getMessage(), (Object) ex);

    return new ResponseEntity<>(exceptionResponse, status);
  }
}
