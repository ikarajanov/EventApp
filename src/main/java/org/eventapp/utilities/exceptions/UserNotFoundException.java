package org.eventapp.utilities.exceptions;

public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException(String message) {
    super(message);
  }
}
