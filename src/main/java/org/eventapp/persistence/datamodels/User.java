package org.eventapp.persistence.datamodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Access(AccessType.FIELD)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @NotNull
  @Column(name = "first_name")
  @Size(max = 250)
  private String firstName;

  @NotNull
  @Column(name = "last_name")
  @Size(max = 250)
  private String lastName;

  @NotNull
  @Column(name = "email")
  @Size(max = 250)
  private String email;

  @NotNull
  @Column(name = "password")
  @Size(max = 250)
  private String password;

  @ManyToOne
  @JoinColumn (name = "location_id", referencedColumnName = "id", nullable = false)
  private Location location = new Location();

  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER, mappedBy = "owner")
  private List<Event> createdEvents = new ArrayList<>();
}
