package org.eventapp.datamodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

  @Column(name = "address")
  @Size(max = 250)
  private String address;

  @Column(name = "city")
  @Size(max = 250)
  private String city;

  @Column(name = "state")
  @Size(max = 250)
  private String state;

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
  private List<User> users = new ArrayList<User>();

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
  private List<Event> events = new ArrayList<Event>();
}
