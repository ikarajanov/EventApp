package org.eventapp.persistence.service;

import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.models.UserModel;

public interface UserPersistenceService {
  
  UserModel createNewUser(UserModel user);

  UserModel getUser(String email, String password);

  List<EventModel> getUserEvents(String userId);
  
}
