package org.eventapp.controllers;

import org.eventapp.models.UserModel;
import org.eventapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class LogInController {

  @Autowired
  private UserService userService;

  @PostMapping(path = "/addNew")
  public UserModel addNewUser(@RequestBody UserModel user) {

    return userService.createNewUser(user);
  }

  @PostMapping(path = "/logIn")
  public UserModel logIn(@RequestBody UserModel user) {

    return userService.getUser(user.getEmail(), user.getPassword());
  }
}
