package org.eventapp.persistence.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.User;
import org.eventapp.persistence.factories.EventModelFactory;
import org.eventapp.persistence.factories.UserModelFactory;
import org.eventapp.persistence.repositories.UserRepository;
import org.eventapp.persistence.service.PersistenceService;
import org.eventapp.persistence.service.UserPersistenceService;
import org.eventapp.utilities.exceptions.IncorrectPasswordException;
import org.eventapp.utilities.exceptions.UserAlreadyExistException;
import org.eventapp.utilities.exceptions.UserEmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@PersistenceService
public class UserPersistenceServiceImpl implements UserPersistenceService {

  @Autowired
  private UserRepository userRepository;

  public void createNewUser(UserModel userModel) {

    User user = UserModelFactory.createUser(userModel);

    boolean emailIsAlreadyExist = checkIfEmailIsAlreadyExist(userModel.getEmail());
    if (emailIsAlreadyExist) {
      throw new UserAlreadyExistException();
    }

    userRepository.save(user);
  }

  public UserModel getUser(String email, String password) {

    User user = userRepository.getUserByEmailAndPassword(email, password);

    if (user == null) {
      user = userRepository.getUserByEmail(email);

      if (user == null) {
        throw new UserEmailNotFoundException();
      }

      throw new IncorrectPasswordException();
    }

    return UserModelFactory.createUserModel(user);
  }

  public List<EventModel> getUserEvents(String userId) {
    User user = userRepository.getUserById(userId);

    if (user == null) {
      return null;
    }

    List<Event> createdEvents = user.getCreatedEvents();
    List<EventModel> userEvents = new ArrayList<EventModel>();

    for (Event event : createdEvents) {
      EventModel eventModel = EventModelFactory.createEventModel(event);
      userEvents.add(eventModel);
    }
    
    return userEvents;
  }


  private boolean checkIfEmailIsAlreadyExist(String email) {

    User user = userRepository.getUserByEmail(email);
    return user != null;
  }
}
