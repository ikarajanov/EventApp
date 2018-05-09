package org.eventapp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleMapsResponseDto {

  List<Object> html_attributions;
  List<LocationDto> results;
  String status;
}
