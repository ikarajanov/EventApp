package org.eventapp.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.eventapp.models.*;
import org.eventapp.services.EventService;
import org.eventapp.services.UserService;
import org.eventapp.utilities.EventUtilities;
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
  public List<EventModel> getUserEvents(
    @RequestParam String userId,
    @RequestParam(required = false) BigDecimal step) {
    
    List<EventModel> userEvents = userService.getUserEvents(userId);
    List<EventModel> filteredEvents = EventUtilities.filterEventsRequiredByStep(userEvents, step);
  
    return filteredEvents;
  }

  @PostMapping(path = "/createNew")
  public List<EventModel> createNewEvent(@RequestBody UpdateEventModel event) {
    
    eventService.createNewEvent(event);
  
    EventModel eventModel = event.getEvent();
    UserModel owner = eventModel.getOwner();
    String userId = owner.getId();
    
    return getUserEvents(userId, BigDecimal.ZERO);
  }
  
  @PostMapping(path = "/deleteEvent")
  public List<EventModel> deleteEvent(String eventId, String userId) {
    
    eventService.deleteEvent(eventId);
    
    return getUserEvents(userId, BigDecimal.ZERO);
  }
  
  @GetMapping(path = "/getNearbyEvents")
  public List<EventModel> getNearbyEvents(
    @RequestParam String userId,
    @RequestParam(required = false) BigDecimal step) {
  
    List<EventModel> userNearbyEvents = eventService.getUserNearbyEvents(userId);
    List<EventModel> filteredEvents = EventUtilities.filterEventsRequiredByStep(userNearbyEvents, step);
  
    return filteredEvents;
  }
  
  @GetMapping(path = "/findBy")
  public List<EventModel> findEvents(
    @RequestParam String userId,
    @RequestParam(required = false) BigDecimal distance,
    @RequestParam(required = false) BigDecimal latitude,
    @RequestParam(required = false) BigDecimal longitude,
    @RequestParam(required = false) String category,
    @RequestParam(required = false) BigDecimal step) {
    List<EventModel> searchedEvents = eventService.findEvents(userId, distance, latitude, longitude, category);
    List<EventModel> filteredEvents = EventUtilities.filterEventsRequiredByStep(searchedEvents, step);
    
    return filteredEvents;
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
