package org.eventapp.services;

import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.models.UserModel;

public interface UserService {

  UserModel getUser(String email, String password);

  UserModel createNewUser(UserModel user);

  List<EventModel> getUserEvents(String userId);
}
