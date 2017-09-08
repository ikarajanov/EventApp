package org.eventapp.controllers;

import org.eventapp.datamodel.User;
import org.eventapp.services.UserService;
import org.eventapp.utilities.exceptions.IncorrectPasswordException;
import org.eventapp.utilities.exceptions.UserAlreadyExistException;
import org.eventapp.utilities.exceptions.UserEmailNotFoundException;
import org.eventapp.utilities.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {

  @Autowired
  private UserService userService;

  @PostMapping( path = "/addNewUser", produces = MediaType.APPLICATION_JSON_VALUE)
  public User addNewUser(@RequestBody User user) {

    User loggedUser = userService.createNewUser(user);

    return loggedUser;
  }

  @PostMapping( path = "/logIn", produces = MediaType.APPLICATION_JSON_VALUE)
  public User logIn(@RequestBody User user) {

    User loggedUser = userService.getUser(user);

    return loggedUser;
  }
}
