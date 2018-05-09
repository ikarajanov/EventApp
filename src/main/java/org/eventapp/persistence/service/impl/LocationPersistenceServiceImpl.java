package org.eventapp.persistence.service.impl;

import org.eventapp.models.LocationModel;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.factories.LocationModelFactory;
import org.eventapp.persistence.datamodels.User;
import org.eventapp.persistence.repositories.LocationRepository;
import org.eventapp.persistence.service.LocationPersistenceService;
import org.eventapp.persistence.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation for {@link LocationPersistenceService}.
 */
@PersistenceService
public class LocationPersistenceServiceImpl implements LocationPersistenceService {
  
  @Autowired
  private LocationRepository locationRepository;
  
  @Override
  public List<Location> getAllOtherLocations(User user) {
    
    try {
      return locationRepository.findAllByUsersIsNotContaining(user);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
  
  @Override
  public Location createNewLocation(LocationModel locationModel) {
    
    Location location = null;
    
    try {
      if (locationModel != null) {
        location = locationRepository.getLocationByGoogleMapUrl(locationModel.getGoogleMapUrl());
    
        if (location == null) {
          location = LocationModelFactory.createLocation(locationModel);
          locationRepository.save(location);
        }
      }
  
      return location;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
  
  @Override
  public Location getLocationByIdLangLat(BigDecimal latitude, BigDecimal longitude) {
  
    Location location = null;
    
    if (latitude != null && longitude != null) {
      try {
  
        location = locationRepository.getLocationByLatitudeAndLongitude(latitude, longitude);
    
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }
    }
    
    return location;
  }
}
