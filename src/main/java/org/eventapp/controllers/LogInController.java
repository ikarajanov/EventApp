package org.eventapp.controllers;

import org.eventapp.models.UserModel;
import org.eventapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class LogInController {

  @Autowired
  private UserService userService;

  @PostMapping( path = "/addNew", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserModel addNewUser(@RequestBody UserModel user) {

    UserModel loggedUser = userService.createNewUser(user);

    return loggedUser;
  }

  @PostMapping( path = "/logIn", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserModel logIn(@RequestBody UserModel user) {

    UserModel loggedUser = userService.getUser(user.getEmail(), user.getPassword());

    return loggedUser;
  }
}
