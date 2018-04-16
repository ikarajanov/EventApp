package org.eventapp.persistence.service;


import org.eventapp.models.EventModel;

/**
 * Event Persistence service.
 */
public interface EventPersistenceService {

  void createNewEvent(EventModel eventModel, String coverPhotoStr);
}
