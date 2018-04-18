package org.eventapp.persistence.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;

/**
 * User Model Factory.
 */
public class UserModelFactory {

  /**
   * Creates new User.
   *
   * @param userModel the {@link UserModel}.
   *
   * @return the new user.
   */
  public static User createUser(UserModel userModel) {

    User user = new User();

    user.setFirstName(userModel.getFirstName());
    user.setLastName(userModel.getLastName());
    user.setEmail(userModel.getEmail());
    user.setPassword(userModel.getPassword());

    LocationModel locationModel = userModel.getLocation();
    Location location = user.getLocation();
    location.setId(locationModel.getId());
  
    return user;
  }

  /**
   * Creates nes User Model.
   *
   * @param user the {@link User}.
   *
   * @return the new user model.
   */
  public static UserModel createUserModel(User user) {

    UserModel userModel = new UserModel();

    userModel.setId(user.getId());
    userModel.setFirstName(user.getFirstName());
    userModel.setLastName(user.getLastName());
    userModel.setEmail(user.getEmail());
    userModel.setPassword(user.getPassword());

    Location location = user.getLocation();
    LocationModel locationModel = LocationModelFactory.createLocationModel(location);
    userModel.setLocation(locationModel);

    return userModel;
  }

  private static List<Event> getUserEvents(Map<String, EventModel> userModelEvents) {

    List<Event> events = new ArrayList<Event>();

    for (String key : userModelEvents.keySet()) {

      EventModel eventModel = userModelEvents.get(key);
//      Event event = EventModelFactory.createEvent(eventModel);
//      events.add(event);
    }

    return events;
  }

  private static Map<String, EventModel> getAllUserDbEvents(List<Event> events) {

    Map<String, EventModel> userModelEvents = new HashMap<String, EventModel>();

    for (Event event : events) {

      String dbId = event.getId();
//      EventModel eventModel = EventModelFactory.createEventModel(event);
//      userModelEvents.put(dbId, eventModel);
    }

    return userModelEvents;
  }
}
