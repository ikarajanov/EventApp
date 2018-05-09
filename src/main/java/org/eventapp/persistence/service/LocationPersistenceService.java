package org.eventapp.persistence.service;

import org.eventapp.models.LocationModel;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Location Persistence Service.
 */
public interface LocationPersistenceService {
  
  List<Location> getAllOtherLocations(User user);
  
  Location createNewLocation(LocationModel locationModel);
  
  Location getLocationByIdLangLat(BigDecimal latitude, BigDecimal longitude);
}
