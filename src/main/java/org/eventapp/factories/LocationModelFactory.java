package org.eventapp.factories;

import org.eventapp.dtos.LocationDto;
import org.eventapp.models.LocationModel;
import org.eventapp.persistence.datamodels.Location;

/**
 * Location Model Factory.
 */
public final class LocationModelFactory {

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
    location.setLongitude(locationModel.getLongitude());

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

    locationModel.setId(location.getId());
    locationModel.setName(location.getName());
    locationModel.setAddress(location.getAddress());
    locationModel.setGoogleMapUrl(location.getGoogleMapUrl());
    locationModel.setLatitude(location.getLatitude());
    locationModel.setLongitude(location.getLongitude());

    return locationModel;
  }
  
  /**
   * Creates Location Model from {@link LocationDto}.
   *
   * @param locationDto the location dto.
   *
   * @return the new location model.
   */
  public static LocationModel createLocationModelFromDto(LocationDto locationDto) {
  
    LocationModel locationModel = new LocationModel();
  
//    locationModel.setName(locationDto.getName());
//    locationModel.setAddress(locationDto.getAddress());
//    locationModel.setGoogleMapUrl(locationDto.getGoogleMapUrl());
//    locationModel.setLatitude(locationDto.getLatitude());
//    locationModel.setLongitude(locationDto.getLongitude());
  
    return locationModel;
  }
}
