package org.eventapp.persistence.service;


import org.eventapp.enums.Category;
import org.eventapp.models.EventModel;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;

import java.util.List;

/**
 * Event Persistence service.
 */
public interface EventPersistenceService {

  void createNewEvent(EventModel eventModel, String coverPhotoStr);
  
  List<EventModel> getAllEventsToGivenLocation(Location location);
  
  void deleteEvent(String eventId);
  
  List<EventModel> getEventsByCategory(Category category, User user);
}
