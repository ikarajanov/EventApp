package org.eventapp.services;

import java.util.List;

import org.eventapp.models.EventModel;


public interface EventService {

  List<EventModel> getFbUserEvents(String accessToken);

  List<String> getEventCategories();

  void createNewEvent(EventModel eventModel);
}
