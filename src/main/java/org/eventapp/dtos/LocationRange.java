package org.eventapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LocationRange {
  
  private BigDecimal lat;
  private BigDecimal lng;
}
