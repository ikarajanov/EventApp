package org.eventapp.persistence.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.User;
import org.eventapp.persistence.mappers.EventModelMapper;
import org.eventapp.persistence.mappers.UserModelMapper;
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
  private UserRepository userRepostory;

  public void createNewUser(UserModel userModel) {

    User user = new User();
    UserModelMapper.mapUserModelToDb(userModel, user);

    boolean emailIsAlreadyExist = checkIfEmailIsAlreadyExist(userModel.getEmail());
    if (emailIsAlreadyExist) {
      throw new UserAlreadyExistException();
    }

    userRepostory.save(user);
  }

  public UserModel getUser(String email, String password) {

    User user = userRepostory.getUserByEmailAndPassword(email, password);

    if (user == null) {
      user = userRepostory.getUserByEmail(email);

      if (user == null) {
        throw new UserEmailNotFoundException();
      }

      throw new IncorrectPasswordException();
    }

    UserModel userModel = new UserModel();

    UserModelMapper.mapUserToUserModel(user, userModel);

    return userModel;
  }

  public List<EventModel> getUserEvents(String userId) {
    User user = userRepostory.getUserById(userId);

    if (user == null) {
      return null;
    }

    List<Event> createdEvents = user.getCreatedEvents();
    List<EventModel> userEvents = new ArrayList<EventModel>();

    for (Event event : createdEvents) {
      EventModel eventModel = new EventModel();
      EventModelMapper.mapEventToEventModel(event, eventModel);
      userEvents.add(eventModel);
    }
    
    return userEvents;
  }


  private boolean checkIfEmailIsAlreadyExist(String email) {

    User user = userRepostory.getUserByEmail(email);
    return user != null;
  }
}
