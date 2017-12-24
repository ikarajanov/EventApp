package org.eventapp.persistence.service.impl;

import org.eventapp.models.EventModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.mappers.EventModelFactory;
import org.eventapp.persistence.repositories.EventRepository;
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

  /**
   * Creates new Event.
   *
   * @param eventModel the {@link EventModel}.
   */
  public void createNewEvent(EventModel eventModel) {

    Event event = EventModelFactory.createEvent(eventModel);
    eventRepository.save(event);
  }
}
