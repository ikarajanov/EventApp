package org.eventapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEventModel {

  private EventModel event;
  private String image;
}
