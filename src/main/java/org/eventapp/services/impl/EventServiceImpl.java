package org.eventapp.services.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.eventapp.dtos.GoogleMapsResponseDto;
import org.eventapp.dtos.LocationDto;
import org.eventapp.enums.Category;
import org.eventapp.factories.EventModelFactory;
import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.models.UpdateEventModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;
import org.eventapp.persistence.repositories.UserRepository;
import org.eventapp.persistence.service.EventPersistenceService;
import org.eventapp.persistence.service.LocationPersistenceService;
import org.eventapp.services.Categories;
import org.eventapp.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of Event Service.
 */
@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventPersistenceService eventPersistenceService;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private LocationPersistenceService locationPersistenceService;
  
  private final String API_KEY = "AIzaSyDkRzpU9HsNoJ35vq8udqIxw1CLs-oDv2E";
  private final BigDecimal DEFAULT_RADIUS = BigDecimal.valueOf(30);
  
  private final String GOOGLE_MAPS_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

  public List<EventModel> getFbUserEvents(String accessToken) {

    return null;
  }

  public List<String> getEventCategories() {
    return Categories.getAllCategories();
  }
  
  @Override
  public List<EventModel> getUserNearbyEvents(String userId) {
  
    User user = userRepository.getUserById(userId);
    
    if (user == null) {
      return new ArrayList<>();
    }
  
    Location location = user.getLocation();
  
    BigDecimal latitude = location.getLatitude();
    BigDecimal longitude = location.getLongitude();
    
    return getNearbyEvents(latitude, longitude, DEFAULT_RADIUS, user);
  }
  
  @Override
  public List<EventModel> getEventsNearLocation(String userId, LocationModel locationModel, BigDecimal radius) {
  
    List<EventModel> events = new ArrayList<>();
    User user = userRepository.getUserById(userId);
    
    if (Objects.nonNull(locationModel) && Objects.nonNull(user)) {
      BigDecimal latitude = locationModel.getLatitude();
      BigDecimal longitude = locationModel.getLongitude();
  
      List<EventModel> nearbyEvents = getNearbyEvents(latitude, longitude, radius, user);
      
      return nearbyEvents;
    }
    
    return events;
  }
  
  @Override
  public List<EventModel> getEventsByCategory(String userId, Category category, BigDecimal radius) {
    List<EventModel> events = new ArrayList<>();
    User user = userRepository.getUserById(userId);
  
    if (Objects.nonNull(category) && Objects.nonNull(user)) {
  
      List<EventModel> eventsByCategory = eventPersistenceService.getEventsByCategory(category, user);
      return eventsByCategory;
    }
  
    return events;
  }
  
  @Override
  public List<EventModel> findEvents(
    String userId,
    BigDecimal distance,
    BigDecimal latitude,
    BigDecimal longitude,
    String category) {
    
    if (StringUtils.isEmpty(userId)) {
      throw new IllegalArgumentException("User id can't be empty!");
    }
  
    User user = userRepository.getUserById(userId);
    
    BigDecimal distanceToEvent = DEFAULT_RADIUS;
    
    if (Objects.nonNull(distance) && distance.compareTo(BigDecimal.ZERO) > 0) {
      distanceToEvent = distance.divide(BigDecimal.valueOf(1000), 0);
    }
  
    if (Objects.nonNull(latitude) && Objects.nonNull(longitude)) {
      List<EventModel> eventsNearLocation = getNearbyEvents(latitude, longitude, distanceToEvent, user);
      
      if (StringUtils.isNotBlank(category)) {
        return getEventsToGivenLocationAndCategory(eventsNearLocation, category);
      }
      
      return eventsNearLocation;
    }
  
    if (StringUtils.isNotBlank(category)) {
      Category categoryEnum = Category.valueOf(category);
      if (Category.None != categoryEnum) {
        List<EventModel> eventsByCategory = eventPersistenceService.getEventsByCategory(categoryEnum, user);
        return eventsByCategory;
      }
    }
    
    return new ArrayList<>();
  }
  
  private List<EventModel> getEventsToGivenLocationAndCategory(
    List<EventModel> eventsToGivenLocation,
    String category) {
  
    List<EventModel> eventModels = eventsToGivenLocation
      .stream()
      .filter(eventModel -> StringUtils.isNotBlank(eventModel.getCategory()))
      .filter(eventModel -> eventModel.getCategory().equals(category))
      .collect(Collectors.toList());
    
    return eventModels;
  }
  
  @Override
  public void createNewEvent(UpdateEventModel event) {
    
    EventModel eventModel = event.getEvent();
    String coverPhoto = event.getImage();
    
    eventPersistenceService.createNewEvent(eventModel, coverPhoto);
  }
  
  @Override
  public void deleteEvent(String eventId) {
    eventPersistenceService.deleteEvent(eventId);
  }
  
  private List<LocationDto> getNearbyLocations(String locationJson) throws IOException {
  
    //create ObjectMapper instance
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    
    //convert json string to object
    GoogleMapsResponseDto response = objectMapper.readValue(locationJson, GoogleMapsResponseDto.class);
  
    return response.getResults();
  }
  
  private List<EventModel> getNearbyEvents(BigDecimal lat, BigDecimal lng, BigDecimal maxDistance, User user) {
    
    
    List<EventModel> nearbyEvents = new ArrayList<>();
  
    List<Location> allLocations = locationPersistenceService.getAllOtherLocations(user);
  
    for (Location location : allLocations) {
      BigDecimal latitude = location.getLatitude();
      BigDecimal longitude = location.getLongitude();
  
      if (latitude != null && longitude != null) {
        float distance = distFrom(lat.floatValue(), lng.floatValue(), latitude.floatValue(), longitude.floatValue());
  
        float maxDistanceInMeters = maxDistance.floatValue() * 1000;
        if (distance <= maxDistanceInMeters) {
  
          List<Event> events = location.getEvents();
  
          List<EventModel> eventModels =
            events.stream().map(event -> {
              try {
                return EventModelFactory.createEventModel(event);
              } catch (SQLException e) {
                e.printStackTrace();
              } catch (IOException e) {
                e.printStackTrace();
              }
              return null;
            }).collect(Collectors.toList());
  
          nearbyEvents.addAll(eventModels);
        }
      }
    }
    
    return nearbyEvents;
  }
  
  
  private static float distFrom(float lat1, float lng1, float lat2, float lng2) {
    
    double earthRadius = 6371000; //meters
    double dLat = Math.toRadians(lat2-lat1);
    double dLng = Math.toRadians(lng2-lng1);
    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
      Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
        Math.sin(dLng/2) * Math.sin(dLng/2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    float dist = (float) (earthRadius * c);
    
    return dist;
  }
}
