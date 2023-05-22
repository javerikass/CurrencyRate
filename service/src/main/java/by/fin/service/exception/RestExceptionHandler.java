package by.fin.service.exception;

import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(CurrencyException.class)
  public ResponseEntity<ErrorResponse> handleCurrencyException(CurrencyException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getStatus(), ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleCurrencyException(ConstraintViolationException ex) {
    int status = HttpStatus.BAD_REQUEST.value();
    ErrorResponse errorResponse = new ErrorResponse(status, ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorResponse> handleValidationException(
      MethodArgumentNotValidException ex) {
    int status = ex.getStatusCode().value();
    ErrorResponse errorResponse = new ErrorResponse(status, ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
  }
}
