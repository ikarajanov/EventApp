package org.eventapp.datamodel;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eventapp.viewmodels.Location;
import org.eventapp.viewmodels.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name = "event")
public class Event {

  @NotNull
  @Column(name = "id")
  @Size(max = 250)
  private String id;

  @NotNull
  @Column(name = "name")
  @Size(max = 250)
  private String name;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
  private User owner;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
  private Location location;

  @Column(name = "number_Of_people_attending")
  private Integer numberOfPeopleAttending;

  @NotNull
  @Column(name = "category")
  @Size(max = 250)
  private String category;


  // private CoverPhoto coverPhoto;

  @NotNull
  @Column(name = "description")
  @Size(max = 250)
  private String description;

  @NotNull
  @Column(name = "start_time")
  private Date startTime;

  @NotNull
  @Column(name = "end_time")
  private Date endTime;

  @NotNull
  @Column(name = "is_canceled")
  private boolean isCanceled = false;
}
