package org.eventapp.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.eventapp.enums.Category;
import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.models.UpdateEventModel;


public interface EventService {

  List<EventModel> getFbUserEvents(String accessToken);

  List<String> getEventCategories();
  
  List<EventModel> getUserNearbyEvents(String userId);
  
  List<EventModel> getEventsNearLocation(String userId, LocationModel locationModel, BigDecimal radius);
  
  List<EventModel> getEventsByCategory(String userId, Category category, BigDecimal radius);
  
  void createNewEvent(UpdateEventModel event);
  
  void deleteEvent(String eventId);
  
  List<EventModel> findEvents(
    String userId,
    BigDecimal distance,
    BigDecimal latitude,
    BigDecimal longitude,
    String category);
}
