package org.eventapp.controllers;

import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.services.EventService;
import org.eventapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/event")
public class EventsController {

  @Autowired
  private EventService eventService;

  @Autowired
  private UserService userService;

  @PostMapping(path = "/getAll")
  public List<EventModel> getAllEvents(@RequestAttribute String userId) {
    return userService.getUserEvents(userId);
  }

  @PostMapping(path = "/createNew", headers = "Accept=application/json")
  public void createNewEvent(@RequestBody EventModel eventModel) {
    eventService.createNewEvent(eventModel);
  }

  @PostMapping(path = "/getAllFbEvents")
  public List<EventModel> getAllFbEvents(@RequestAttribute String accessToken) {
    return eventService.getFbUserEvents(accessToken);
  }

  @GetMapping(path = "/getCategories")
  public List<String> getEventCategories() {
    return eventService.getEventCategories();
  }
}
