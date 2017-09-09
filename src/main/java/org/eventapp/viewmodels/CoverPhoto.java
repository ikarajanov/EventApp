package org.eventapp.viewmodels;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class CoverPhoto {

  private String id;
  private String cover_id;
  private String offset_x;
  private String offset_y;
  private String sourceURL;
}
