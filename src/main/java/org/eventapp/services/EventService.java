package org.eventapp.services;

import java.math.BigDecimal;
import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.models.UpdateEventModel;


public interface EventService {

  List<EventModel> getFbUserEvents(String accessToken);

  List<String> getEventCategories();
  
  List<EventModel> getNearbyEvents(String userId, BigDecimal radius);

  void createNewEvent(UpdateEventModel event);
}
