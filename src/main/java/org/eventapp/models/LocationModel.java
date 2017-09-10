package org.eventapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LocationModel {

  private String id;
  private String name;
  private String city;
  private String country;
  private float latitude;
  private float longitude;
}
