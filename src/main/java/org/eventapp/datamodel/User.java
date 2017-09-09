package org.eventapp.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

  @NotNull
  @Column(name = "id")
  @Size(max = 250)
  private String id;

  @NotNull
  @Column(name = "first_name")
  @Size(max = 250)
  private String firstName;

  @NotNull
  @Column(name = "second_name")
  @Size(max = 250)
  private String secondName;

  @NotNull
  @Column(name = "email")
  @Size(max = 250)
  private String email;

  @NotNull
  @Column(name = "password")
  @Size(max = 250)
  private String password;

  @NotNull
  @ManyToOne
  @JoinColumn (name = "location_id", referencedColumnName = "id", nullable = false)
  private Location location;

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private List<Event> events = new ArrayList<Event>();
}
