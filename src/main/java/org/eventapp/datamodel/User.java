package org.eventapp.datamodel;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class User {

  private String email;
  private String password;
  private String address;
  private String city;
  private String state;
  private String zip;
}
