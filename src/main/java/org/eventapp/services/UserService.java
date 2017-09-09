package org.eventapp.services;

import org.eventapp.viewmodels.User;

public interface UserService {

  User getUser(User user);

  User createNewUser(User user);

}
