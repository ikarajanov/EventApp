package org.eventapp.persistence.datamodels;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.time.LocalDateTime;

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
  private User owner = new User();

  @ManyToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
  private Location location = new Location();

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
  private LocalDateTime startTime;

  @NotNull
  @Column(name = "end_time")
  private LocalDateTime endTime;

  @NotNull
  @Column(name = "is_canceled")
  private boolean isCanceled = false;
  
  @Column(name = "cover_photo")
  private Blob coverPhoto;
}
