package org.eventapp.services.impl;

import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.service.UserPersistenceService;
import org.eventapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserPersistenceService userPersistenceService;

  public UserModel getUser(String email, String pasword) {

    return userPersistenceService.getUser(email, pasword);
  }

  public UserModel createNewUser(UserModel user) {

    userPersistenceService.createNewUser(user);

    return user;
  }

  public List<EventModel> getUserEvents(String userId) {
    return userPersistenceService.getUserEvents(userId);
  }
}
