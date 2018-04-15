package org.eventapp.persistence.repositories;

import org.eventapp.persistence.datamodels.Location;

public interface LocationRepository extends BaseRepository<Location> {
  
  Location getLocationByGoogleMapUrl(String googleMapUtl);
}
