package org.eventapp.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.eventapp.viewmodels.Event;
import org.eventapp.services.EventService;
import org.eventapp.utilities.EventUtilities;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  public List<Event> getFbUserEvents(String accessToken) {

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

  public List<Event> getUserEvents(String userId) {
    return EventUtilities.getMockEvents();
  }
}
