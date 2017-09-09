package org.eventapp.controllers;

import java.util.List;

import org.eventapp.viewmodels.Event;
import org.eventapp.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/event")
public class EventsController {

  @Autowired
  private EventService eventService;

  @PostMapping(path = "/getAll")
  public List<Event> getAllEvents(String userId) {
    return eventService.getUserEvents(userId);
  }

  @PostMapping(path = "/getAllFbEvents")
  public List<Event> getAllFbEvents(String accessToken) {
    return eventService.getFbUserEvents(accessToken);
  }
}
