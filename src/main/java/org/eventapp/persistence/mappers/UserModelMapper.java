package org.eventapp.persistence.mappers;

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
public class UserModelMapper {

  public static void mapUserModelToDb(UserModel userModel, User user) {

    user.setFirstName(userModel.getFirstName());
    user.setLastName(userModel.getLastName());
    user.setEmail(userModel.getEmail());
    user.setPassword(userModel.getPassword());
    user.setCreatedEvents(getUserEvents(userModel.getCreatedEvents()));

    Location location = user.getLocation();
    LocationModel locationModel = new LocationModel();
    LocationModelMapper.mapLocationModelToDb(locationModel, location);
    user.setLocation(location);
  }

  public static void mapUserToUserModel(User user, UserModel userModel) {

    userModel.setFirstName(user.getFirstName());
    userModel.setLastName(user.getLastName());
    userModel.setEmail(user.getEmail());
    userModel.setPassword(user.getPassword());
    Location location = user.getLocation();
    LocationModel locationModel = new LocationModel();
    LocationModelMapper.mapLocationToLocationModel(location, locationModel);
    userModel.setLocation(locationModel);
    userModel.setCreatedEvents(getAllUserDbEvents(user.getCreatedEvents()));
  }

  private static List<Event> getUserEvents(Map<String, EventModel> userModelEvents) {

    List<Event> events = new ArrayList<Event>();

    for (String key : userModelEvents.keySet()) {
      EventModel eventModel = userModelEvents.get(key);
      Event event = new Event();
      EventModelMapper.mapEventModelToDb(eventModel, event);

      events.add(event);
    }

    return events;
  }

  private static Map<String, EventModel> getAllUserDbEvents(List<Event> events) {

    Map<String, EventModel> userModelEvents = new HashMap<String, EventModel>();


    for (Event event : events) {
      String dbId = event.getId();

      EventModel eventModel = userModelEvents.get(dbId);

      if (eventModel == null) {
        eventModel = new EventModel();
      }

      EventModelMapper.mapEventToEventModel(event, eventModel);

      userModelEvents.put(dbId, eventModel);
    }

    return userModelEvents;
  }
}
