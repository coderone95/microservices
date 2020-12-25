package com.coderone95.user.service.exceptions;

import com.coderone95.user.service.model.ErrorDetails;
import com.coderone95.user.service.model.ErrorResponse;
import com.coderone95.user.service.model.ExceptionDetails;
import com.coderone95.user.service.model.Status;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserGenericException.class)
    public ResponseEntity<?> genericExceptionForRole(UserGenericException ex, WebRequest req){
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        Status status = new Status();
        status.setStatus("ERROR");
        err.setStatus(status);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorList);
        return handleExceptionInternal(null, errorDetails, null, errorDetails.getStatus(), request);
    }
}
