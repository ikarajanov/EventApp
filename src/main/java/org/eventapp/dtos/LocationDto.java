package org.eventapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {
  
  private Geometry geometry;
  private String name;
  private String icon;
}
