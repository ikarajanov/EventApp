package org.eventapp.controllers;

import java.math.BigDecimal;
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

  @GetMapping(path = "/getUserEvents")
  public List<EventModel> getUserEvents(@RequestParam String userId) {
    return userService.getUserEvents(userId);
  }

  @PostMapping(path = "/createNew")
  public void createNewEvent(@RequestBody UpdateEventModel event) {
    eventService.createNewEvent(event);
  }
  
  @GetMapping(path = "/getNearbyEvents")
  public List<EventModel> getNearbyEvents(@RequestParam String userId, @RequestParam BigDecimal radius) {
    return eventService.getNearbyEvents(userId, radius);
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
