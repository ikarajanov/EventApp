package org.eventapp.persistence.repositories;

import org.eventapp.persistence.datamodels.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface EventRepository extends CrudRepository<Event, String> {

  List<Event> getAllEventsByLocationId(String locationId);
  
  @Modifying
  @Transactional
  Integer deleteEventById(String id);
}
