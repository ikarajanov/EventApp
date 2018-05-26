package org.eventapp.persistence.service.impl;

import org.eventapp.enums.Category;
import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.factories.EventModelFactory;
import org.eventapp.persistence.datamodels.User;
import org.eventapp.persistence.repositories.EventRepository;
import org.eventapp.persistence.service.EventPersistenceService;
import org.eventapp.persistence.service.LocationPersistenceService;
import org.eventapp.persistence.service.PersistenceService;
import org.eventapp.persistence.service.UserPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Event Persistence service.
 */
@PersistenceService
public class EventPersistenceServiceImpl implements EventPersistenceService {

  @Autowired
  private EventRepository eventRepository;
  
  @Autowired
  private LocationPersistenceService locationPersistenceService;
  
  @Autowired
  private UserPersistenceService userPersistenceService;
  
  /**
   * Creates new Event.
   *
   * @param eventModel the {@link EventModel}.
   */
  public void createNewEvent(EventModel eventModel, String coverPhotoStr) {

    try {
      LocationModel locationModel = eventModel.getLocation();
      Location location = locationPersistenceService.createNewLocation(locationModel);
      locationModel.setId(location.getId());
  
      UserModel owner = eventModel.getOwner();
  
      UserModel user = userPersistenceService.getUser(owner.getEmail(), owner.getPassword());
      
      if (user == null) {
        user = userPersistenceService.createNewUser(owner);
      }
      
      owner.setId(user.getId());
  
      Event event = EventModelFactory.createEvent(eventModel, coverPhotoStr);

      eventRepository.save(event);

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
  
  @Override
  public List<EventModel> getAllEventsToGivenLocation(Location location) {
    
    try {
  
      List<EventModel> list = new ArrayList<>();
  
      if (location != null) {
        List<Event> allEventsByLocation = eventRepository.getAllEventsByLocationId(location.getId());
  
        for (Event event : allEventsByLocation) {
          EventModel eventModel = EventModelFactory.createEventModel(event);
          list.add(eventModel);
        }
      }
      
      return list;
      
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
  
  @Override
  public void deleteEvent(String eventId) {
  
    try {
      eventRepository.delete(eventId);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
  
  @Override
  public List<EventModel> getEventsByCategory(Category category, User user) {
    
      List<Event> eventsByCategory =
        eventRepository.findAllByOwnerIdIsNotContainingAndCategory(user.getId(), category.toString());
  
      List<EventModel> eventModels = new ArrayList<>();
      
      for (Event event : eventsByCategory) {
        EventModel eventModel = null;
        try {
          eventModel = EventModelFactory.createEventModel(event);
        } catch (SQLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        eventModels.add(eventModel);
      }
      
      return eventModels;
  }
}
