package org.eventapp.persistence.repositories;

import org.eventapp.persistence.datamodels.Event;

import java.util.List;


public interface EventRepository extends BaseRepository<Event> {

  List<Event> getAllEventsByLocation(String locationId);
}
