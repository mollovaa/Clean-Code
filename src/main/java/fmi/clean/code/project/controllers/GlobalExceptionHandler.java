package fmi.clean.code.project.controllers;

import fmi.clean.code.project.exceptions.InvalidInputException;
import fmi.clean.code.project.helpers.ResponseMessage;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler( {InvalidInputException.class, ValidationException.class})
  public ResponseMessage handleInvalidInput(Exception ex) {
    return new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler( {MethodArgumentNotValidException.class})
  public ResponseMessage handleArgumentNotValid(MethodArgumentNotValidException ex) {
    List<String> errorList = ex
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    return new ResponseMessage(HttpStatus.BAD_REQUEST, errorList.toString());
  }

}