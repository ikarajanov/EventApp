package org.eventapp.persistence.service;

import org.eventapp.models.LocationModel;
import org.eventapp.persistence.datamodels.Location;

/**
 * Location Persistence Service.
 */
public interface LocationPersistenceService {
  
  Location createNewLocation(LocationModel locationModel);
}
