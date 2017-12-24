package org.eventapp.persistence.mappers;

import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;
import org.eventapp.utilities.DateUtility;

/**
 * Event Model Factory.
 */
public class EventModelFactory {

  /**
   * Creates new Event.
   *
   * @param eventModel the {@link EventModel}.
   *
   * @return the new event.
   */
  public static Event createEvent(EventModel eventModel) {

    Event event = new Event();

    event.setId(eventModel.getId());
    event.setName(eventModel.getName());

    UserModel eventOwner = eventModel.getOwner();
    User owner = UserModelFactory.createUser(eventOwner);
    event.setOwner(owner);

    LocationModel locationModel = eventModel.getLocation();
    Location location = LocationModelMapper.createLocation(locationModel);
    event.setLocation(location);

    event.setNumberOfPeopleAttending(eventModel.getNumberOfPeopleAttending());
    event.setCategory(eventModel.getCategory());
    // eventModel.setcoverPhoto();
    event.setDescription(eventModel.getDescription());
    event.setStartTime(DateUtility.getDateFromString(eventModel.getStartTime()));
    event.setEndTime(DateUtility.getDateFromString(eventModel.getEndTime()));
    event.setCanceled(eventModel.isCanceled());

    return event;
  }

  /**
   * Creates new Event Model.
   *
   * @param event the {@link Event}.
   *
   * @return the new event model.
   */
  public static EventModel createEventModel(Event event) {

    EventModel eventModel = new EventModel();

    eventModel.setId(event.getId());
    eventModel.setName(event.getName());

    User owner = event.getOwner();
    UserModel eventOwner = UserModelFactory.createUserModel(owner);
    eventModel.setOwner(eventOwner);

    Location location = event.getLocation();
    LocationModel locationModel = LocationModelMapper.createLocationModel(location);
    eventModel.setLocation(locationModel);

    eventModel.setNumberOfPeopleAttending(event.getNumberOfPeopleAttending());
    eventModel.setCategory(event.getCategory());
    // eventModel.setcoverPhoto();
    eventModel.setDescription(event.getDescription());
    eventModel.setStartTime(DateUtility.getStringFromDate(event.getStartTime()));
    eventModel.setEndTime(DateUtility.getStringFromDate(event.getEndTime()));
    eventModel.setCanceled(event.isCanceled());

    return eventModel;
  }
}
