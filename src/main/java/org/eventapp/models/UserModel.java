package org.eventapp.models;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserModel {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private LocationModel location;
  private Map<String, EventModel> createdEvents = new HashMap<String, EventModel>();
}
