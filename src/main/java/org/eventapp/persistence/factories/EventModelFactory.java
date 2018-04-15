package org.eventapp.persistence.factories;

import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

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
    User owner = event.getOwner();
    owner.setId(eventOwner.getId());
  
    LocationModel locationModel = eventModel.getLocation();
    Location location = event.getLocation();
    location.setId(locationModel.getId());

    event.setNumberOfPeopleAttending(eventModel.getNumberOfPeopleAttending());
    event.setCategory(eventModel.getCategory());

    // eventModel.setcoverPhoto();
    event.setDescription(eventModel.getDescription());

    String startTime = eventModel.getStartTime();
    String startDate = eventModel.getStartDate();
    event.setStartTime(createLocalDate(startDate, startTime));

    String endTime = eventModel.getEndTime();
    String endDate = eventModel.getEndDate();
    event.setEndTime(createLocalDate(endDate, endTime));
    
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
    LocationModel locationModel = LocationModelFactory.createLocationModel(location);
    eventModel.setLocation(locationModel);

    eventModel.setNumberOfPeopleAttending(event.getNumberOfPeopleAttending());
    eventModel.setCategory(event.getCategory());
    // eventModel.setcoverPhoto();
    eventModel.setDescription(event.getDescription());
    LocalDateTime startTime = event.getStartTime();
    if (startTime != null) {
      eventModel.setStartTime(startTime.toString());
    }

    LocalDateTime endTime = event.getEndTime();
    if (endTime != null) {
      eventModel.setEndTime(endTime.toString());
    }

    eventModel.setCanceled(event.isCanceled());

    return eventModel;
  }
  
  private static LocalDateTime createLocalDate(String date, String time) {
  
    LocalDateTime endLocalDateTime = null;
    
    if (!StringUtils.isEmpty(time) && !StringUtils.isEmpty(date)) {
      String endTimeStr = time.substring(0, time.length() - 2);
      LocalDateTime endLocalTime = LocalDateTime.parse(endTimeStr);
  
      String endDateStr = date.substring(0, date.length() - 2);
      LocalDateTime endLocalDate = LocalDateTime.parse(endDateStr);
  
      endLocalDateTime =
        LocalDateTime.of(
          endLocalDate.getYear(),
          endLocalDate.getMonth(),
          endLocalDate.getDayOfMonth(),
          endLocalTime.getHour() + 1,
          endLocalTime.getMinute());
    }
    
    return endLocalDateTime;
  }
}
