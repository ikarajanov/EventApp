package org.eventapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventModel {

  private String id;
  private String name;
  private UserModel owner;
  private LocationModel location;
  private Integer numberOfPeopleAttending;
  private String category;
  private CoverPhotoModel coverPhoto;
  private String description;
  private String startDate;
  private String startTime;
  private String endDate;
  private String endTime;
  private boolean isCanceled;
}
