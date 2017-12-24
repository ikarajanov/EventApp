package org.eventapp.persistence.mappers;

import org.eventapp.models.LocationModel;
import org.eventapp.persistence.datamodels.Location;
public class LocationModelMapper {

  /**
   * Creates Location,
   *
   * @param locationModel the {@link LocationModel}.
   *
   * @return the new location.
   */
  public static Location createLocation(LocationModel locationModel) {

    Location location = new Location();

    location.setName(locationModel.getName());
    location.setAddress(locationModel.getAddress());
    location.setGoogleMapUrl(locationModel.getGoogleMapUrl());
    location.setLatitude(locationModel.getLatitude());
    location.setLongitude(location.getLongitude());

    return location;
  }

  /**
   * Creates Location Model.
   *
   * @param location the {@link Location}.
   *
   * @return the new location model.
   */
  public static LocationModel createLocationModel(Location location) {

    LocationModel locationModel = new LocationModel();

    locationModel.setName(location.getName());
    locationModel.setAddress(location.getAddress());
    locationModel.setGoogleMapUrl(location.getGoogleMapUrl());
    locationModel.setLatitude(location.getLatitude());
    locationModel.setLongitude(location.getLongitude());

    return locationModel;
  }
}
