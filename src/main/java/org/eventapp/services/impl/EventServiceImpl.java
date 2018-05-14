package org.eventapp.services.impl;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.eventapp.dtos.Geometry;
import org.eventapp.dtos.GoogleMapsResponseDto;
import org.eventapp.dtos.LocationDto;
import org.eventapp.dtos.LocationRange;
import org.eventapp.factories.EventModelFactory;
import org.eventapp.factories.LocationModelFactory;
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
import org.eventapp.utilities.deserializers.LocationDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
  
  private final String GOOGLE_MAPS_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

  public List<EventModel> getFbUserEvents(String accessToken) {
//
//    try {
////      String urlString = "http://graph.facebook.com/v2.10/" + accessToken + "/events";
////      URL url = new URL(urlString);
////      HttpURLConnection con = (HttpURLConnection) url.openConnection();
////      con.setRequestMethod("GET");
////      int status = con.getResponseCode();
////
////      // TODO
////      if (status == HttpURLConnection.HTTP_OK) {
////        BufferedReader in = new BufferedReader(
////          new InputStreamReader(con.getInputStream()));
////        String inputLine;
////        StringBuffer content = new StringBuffer();
////        while ((inputLine = in.readLine()) != null) {
////          content.append(inputLine);
////        }
////        in.close();
////      }
//    } catch (MalformedURLException e) {
//      e.printStackTrace();
//    } catch (ProtocolException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    return null;
  }

  public List<String> getEventCategories() {
    return Categories.getAllCategories();
  }
  
  @Override
  public List<EventModel> getNearbyEvents(String userId, BigDecimal radius) {
  
    User user = userRepository.getUserById(userId);
    
    if (user == null) {
      return new ArrayList<>();
    }
  
    Location location = user.getLocation();
  
    BigDecimal latitude = location.getLatitude();
    BigDecimal longitude = location.getLongitude();
//
//    String urlString =
//      GOOGLE_MAPS_URL + "location="
//        + latitude
//        + "," + longitude
//        + "&radius=" + radius
//        + "&key=" + API_KEY;
//
//    URL url;
//    try {
//      url = new URL(urlString);
//
//      HttpURLConnection con = (HttpURLConnection) url.openConnection();
//      con.setRequestMethod("GET");
//      int status = con.getResponseCode();
//
//      if (status == HttpURLConnection.HTTP_OK) {
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuilder content = new StringBuilder();
//
//        while ((inputLine = in.readLine()) != null) {
//          content.append(inputLine);
//        }
//        in.close();
//
//        String locationJson = content.toString();
//
////        List<LocationDto> locationDtos = getNearbyLocations(locationJson);
//
//      }
//
//    } catch (MalformedURLException e) {
//      e.printStackTrace();
//    } catch (ProtocolException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//
//    }
//
    return getNearbyEvents(latitude, longitude, radius, user);
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
  
//    for (LocationDto locationModel : locationDtos) {
//
//      Geometry geometry = locationModel.getGeometry();
//      LocationRange location = geometry.getLocation();
//
//      Location eventLocation = locationPersistenceService.getLocationByIdLangLat(location.getLat(), location.getLng());
//
//      List<EventModel> allEventsToGivenLocation =
//        eventPersistenceService.getAllEventsToGivenLocation(eventLocation);
//
//      nearbyEvents.addAll(allEventsToGivenLocation);
//    }
    
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
