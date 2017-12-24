package org.eventapp.persistence.datamodels;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Access(AccessType.FIELD)
@Table(name = "locations")
public class Location extends BaseEntity {

  @Column(name = "name")
  @Size(max = 250)
  private String name;

  @Column(name = "address")
  @Size(max = 250)
  private String address;

  @Column(name = "google_map_url")
  @Size(max = 500)
  private String googleMapUrl;

  @Column(name = "latitude")
  private BigDecimal latitude;

  @Column(name = "longitude")
  private BigDecimal longitude;

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "location")
  private List<User> users = new ArrayList<>();

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(cascade = CascadeType.ALL,   fetch = FetchType.EAGER, mappedBy = "location")
  private List<Event> events = new ArrayList<>();
}

