package org.eventapp.persistence.repositories;

import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;

import java.math.BigDecimal;
import java.util.List;

public interface LocationRepository extends BaseRepository<Location> {
  
  List<Location> findAllByUsersIsNotContaining(User user);
  
  Location getLocationByGoogleMapUrl(String googleMapUtl);
  
  Location getLocationByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
