package org.eventapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CoverPhotoModel {

  private String id;
  private String cover_id;
  private String offset_x;
  private String offset_y;
  private String sourceURL;
}
