package org.eventapp.viewmodels;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class Location {

  private String id;
  private String name;
  private String city;
  private String country;
  private float latitude;
  private float longitude;
}
