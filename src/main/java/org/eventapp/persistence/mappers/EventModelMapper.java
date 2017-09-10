package org.eventapp.persistence.mappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;
import org.eventapp.utilities.DateUtility;
public class EventModelMapper {

  public static void mapEventModelToDb(EventModel eventModel, Event event) {

    event.setId(eventModel.getId());
    event.setName(eventModel.getName());
    User owner = event.getOwner();
    UserModel eventOwner = eventModel.getOwner();
    UserModelMapper.mapUserModelToDb(eventOwner, owner);
    event.setOwner(owner);
    LocationModel locationModel = eventModel.getLocation();
    Location location = new Location();
    LocationModelMapper.mapLocationModelToDb(locationModel, location);
    event.setLocation(location);
    event.setNumberOfPeopleAttending(eventModel.getNumberOfPeopleAttending());
    event.setCategory(eventModel.getCategory());
    // eventModel.setcoverPhoto();
    event.setDescription(eventModel.getDescription());
    event.setStartTime(DateUtility.getDateFromString(eventModel.getStartTime()));
    event.setEndTime(DateUtility.getDateFromString(eventModel.getEndTime()));
    event.setCanceled(eventModel.isCanceled());
  }

  public static void mapEventToEventModel(Event event, EventModel eventModel) {

    eventModel.setId(event.getId());
    eventModel.setName(event.getName());
    User owner = event.getOwner();
    UserModel eventOwner = new UserModel();
    UserModelMapper.mapUserToUserModel(owner, eventOwner);
    eventModel.setOwner(eventOwner);
    LocationModel locationModel = eventModel.getLocation();
    Location location = new Location();
    LocationModelMapper.mapLocationToLocationModel(location, locationModel);
    eventModel.setLocation(locationModel);
    eventModel.setNumberOfPeopleAttending(event.getNumberOfPeopleAttending());
    eventModel.setCategory(event.getCategory());
    // eventModel.setcoverPhoto();
    eventModel.setDescription(event.getDescription());
    eventModel.setStartTime(DateUtility.getStringFromDate(event.getStartTime()));
    eventModel.setEndTime(DateUtility.getStringFromDate(event.getEndTime()));
    eventModel.setCanceled(event.isCanceled());
  }
}
