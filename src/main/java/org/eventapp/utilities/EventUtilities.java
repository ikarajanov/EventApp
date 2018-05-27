package org.eventapp.utilities;

import org.eventapp.models.EventModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class EventUtilities {
  
  public static List<EventModel> filterEventsRequiredByStep(List<EventModel> events, BigDecimal step) {
  
    long toSkip = step.longValue() * 6;
    
    List<EventModel> eventsToRet =
      events
        .stream()
        .skip(toSkip)
        .limit(6)
        .collect(Collectors.toList());
    
    return eventsToRet;
  }
}
