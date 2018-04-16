package org.eventapp.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.eventapp.models.EventModel;
import org.eventapp.models.UpdateEventModel;
import org.eventapp.persistence.service.EventPersistenceService;
import org.eventapp.services.Categories;
import org.eventapp.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of Event Service.
 */
@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventPersistenceService eventPersistenceService;

  public List<EventModel> getFbUserEvents(String accessToken) {

    try {
      String urlString = "http://graph.facebook.com/v2.10/" + accessToken + "/events";
      URL url = new URL(urlString);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      int status = con.getResponseCode();

      // TODO
      if (status == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          content.append(inputLine);
        }
        in.close();
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (ProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public List<String> getEventCategories() {
    return Categories.getAllCategories();
  }

  public void createNewEvent(UpdateEventModel event) {
  
    EventModel eventModel = event.getEvent();
    String coverPhoto = event.getImage();
  
    eventPersistenceService.createNewEvent(eventModel, coverPhoto);
  }
}
