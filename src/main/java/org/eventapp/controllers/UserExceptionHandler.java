package org.eventapp.controllers;

import org.eventapp.utilities.exceptions.IncorrectPasswordException;
import org.eventapp.utilities.exceptions.UserAlreadyExistException;
import org.eventapp.utilities.exceptions.UserEmailNotFoundException;
import org.eventapp.utilities.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandler {

  private final static String EMAIL_NOT_EXIST = "emailNotExist";
  private final static String INCORRECT_PASSWORD = "incorrectPassword";
  private final static String USER_ALREADY_EXIST = "userAlreadyExist";

  @ExceptionHandler(UserEmailNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody Error userEmailNotExist() {
    return new Error(EMAIL_NOT_EXIST);
  }

  @ExceptionHandler(IncorrectPasswordException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public @ResponseBody Error incorrectPassword() {
    return new Error(INCORRECT_PASSWORD);
  }

  @ExceptionHandler(UserAlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody Error userAlreadyExist() {
    return new Error(USER_ALREADY_EXIST);
  }
}
