package org.eventapp.services;

import java.util.List;

import org.eventapp.datamodel.Event;


public interface EventService {

  List<Event> getUserEvents(String userId);

  List<Event> getFbUserEvents(String accessToken);
}
