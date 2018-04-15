package org.eventapp.persistence.service.impl;

import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.factories.EventModelFactory;
import org.eventapp.persistence.factories.LocationModelFactory;
import org.eventapp.persistence.repositories.EventRepository;
import org.eventapp.persistence.repositories.LocationRepository;
import org.eventapp.persistence.service.EventPersistenceService;
import org.eventapp.persistence.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of Event Persistence service.
 */
@PersistenceService
public class EventPersistenceServiceImpl implements EventPersistenceService {

  @Autowired
  private EventRepository eventRepository;
  
  @Autowired
  private LocationRepository locationRepository;
  
  /**
   * Creates new Event.
   *
   * @param eventModel the {@link EventModel}.
   */
  public void createNewEvent(EventModel eventModel) {

    try {
      LocationModel locationModel = eventModel.getLocation();
      if (locationModel != null) {
        Location location = locationRepository.getLocationByGoogleMapUrl(locationModel.getGoogleMapUrl());

        if (location == null) {
          location = LocationModelFactory.createLocation(locationModel);
          locationRepository.save(location);
        }

        String locationModelId = location.getId();
        locationModel.setId(locationModelId);
      }
      
      Event event = EventModelFactory.createEvent(eventModel);

      eventRepository.save(event);

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
