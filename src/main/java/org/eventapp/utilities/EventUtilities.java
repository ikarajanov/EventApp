package org.eventapp.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eventapp.datamodel.Event;
import org.eventapp.utilities.enums.EventCategory;

public class EventUtilities {


  public static List<Event> getMockEvents() {

    List<Event> events = new ArrayList<Event>();

    for (int i = 0 ; i < 6; i++) {
      Event.EventBuilder builder = Event.builder();

      builder.id(i + "");
      builder.name("Event - " + i);
      builder.description("Test");
      builder.category(EventCategory.college.name());
      Calendar calendar = Calendar.getInstance();
      Date date = new Date();
      calendar.setTime(date);
      Date startTime = calendar.getTime();
      builder.startTime(startTime.toString());
      calendar.add(Calendar.MONTH, 1);
      Date endTime = calendar.getTime();
      builder.endTime(endTime.toString());
      builder.isCanceled(false);

      events.add(builder.build());
    }

    return events;
  }
}
