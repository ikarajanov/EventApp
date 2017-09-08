package org.eventapp.datamodel;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Event {

  private String id;
  private String name;
  private User owner;
  private Location location;
  private Integer numberOfPeopleAttending;
  private String category;
  private CoverPhoto coverPhoto;
  private String description;
  private String startTime;
  private String endTime;
  private boolean isCanceled;
}
