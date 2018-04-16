package org.eventapp.controllers;

import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.models.UpdateEventModel;
import org.eventapp.services.EventService;
import org.eventapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/event")
public class EventsController {

  @Autowired
  private EventService eventService;

  @Autowired
  private UserService userService;

  @GetMapping(path = "/getAll")
  public List<EventModel> getAllEvents(@RequestParam String userId) {
    return userService.getUserEvents(userId);
  }

  @PostMapping(path = "/createNew")
  public void createNewEvent(@RequestBody UpdateEventModel event) {
    eventService.createNewEvent(event);
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
