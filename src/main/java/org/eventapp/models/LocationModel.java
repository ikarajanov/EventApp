package org.eventapp.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class LocationModel {

  private String id;
  private String name;
  private String address;
  private String googleMapUrl;
  private BigDecimal latitude;
  private BigDecimal longitude;
}
