package org.eventapp.persistence.datamodels;


import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Access(AccessType.FIELD)
@Entity
@Table(name = "events")
public class Event extends BaseEntity {

  @NotNull
  @Column(name = "name")
  @Size(max = 250)
  private String name;

  @ManyToOne
  @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
  private User owner;

  @ManyToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
  private Location location;

  @Column(name = "number_of_people_attending")
  private Integer numberOfPeopleAttending;

  @NotNull
  @Size(max = 250)
  private String category;


  // private CoverPhoto coverPhoto;

  @NotNull
  @Column(name = "description")
  @Size(max = 500)
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
