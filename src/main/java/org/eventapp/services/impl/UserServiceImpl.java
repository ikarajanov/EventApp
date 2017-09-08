package org.eventapp.services.impl;

import org.eventapp.datamodel.User;
import org.eventapp.services.UserService;
import org.eventapp.utilities.exceptions.IncorrectPasswordException;
import org.eventapp.utilities.exceptions.UserAlreadyExistException;
import org.eventapp.utilities.exceptions.UserEmailNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  public User getUser(User user) {
    return user;
  }

  public User createNewUser(User user) {
    return user;
  }
}
