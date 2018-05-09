package org.eventapp.factories;

import org.apache.commons.codec.binary.Base64;
import org.eventapp.models.EventModel;
import org.eventapp.models.LocationModel;
import org.eventapp.models.UserModel;
import org.eventapp.persistence.datamodels.Event;
import org.eventapp.persistence.datamodels.Location;
import org.eventapp.persistence.datamodels.User;
import org.springframework.util.StringUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Event Model Factory.
 */
public final class EventModelFactory {

  /**
   * Creates new Event.
   *
   * @param eventModel the {@link EventModel}.
   *
   * @return the new event.
   */
  public static Event createEvent(EventModel eventModel, String coverPhotoStr) throws SQLException {

    Event event = new Event();

    event.setId(eventModel.getId());
    event.setName(eventModel.getName());

    UserModel eventOwner = eventModel.getOwner();
    User owner = event.getOwner();
    owner.setId(eventOwner.getId());
  
    LocationModel locationModel = eventModel.getLocation();
    Location location = event.getLocation();
    location.setId(locationModel.getId());

    event.setNumberOfPeopleAttending(eventModel.getNumberOfPeopleAttending());
    event.setCategory(eventModel.getCategory());

    Blob coverPhoto = createBlobFromString(coverPhotoStr);
    event.setCoverPhoto(coverPhoto);
    
    event.setDescription(eventModel.getDescription());

    String startTime = eventModel.getStartTime();
    String startDate = eventModel.getStartDate();
    event.setStartTime(createLocalDate(startDate, startTime));

    String endTime = eventModel.getEndTime();
    String endDate = eventModel.getEndDate();
    event.setEndTime(createLocalDate(endDate, endTime));
    
    event.setCanceled(eventModel.isCanceled());

    return event;
  }

  /**
   * Creates new Event Model.
   *
   * @param event the {@link Event}.
   *
   * @return the new event model.
   */
  public static EventModel createEventModel(Event event) throws SQLException, IOException {

    EventModel eventModel = new EventModel();

    eventModel.setId(event.getId());
    eventModel.setName(event.getName());

    User owner = event.getOwner();
    UserModel eventOwner = UserModelFactory.createUserModel(owner);
    eventModel.setOwner(eventOwner);

    Location location = event.getLocation();
    LocationModel locationModel = LocationModelFactory.createLocationModel(location);
    eventModel.setLocation(locationModel);

    eventModel.setNumberOfPeopleAttending(event.getNumberOfPeopleAttending());
    eventModel.setCategory(event.getCategory());
  
    Blob coverPhoto = event.getCoverPhoto();
    String base64Photo = createStringFromBlob(coverPhoto);
    eventModel.setCoverPhoto(base64Photo);
    
    eventModel.setDescription(event.getDescription());
    LocalDateTime startTime = event.getStartTime();
    if (startTime != null) {
      eventModel.setStartTime(startTime.toString());
    }

    LocalDateTime endTime = event.getEndTime();
    if (endTime != null) {
      eventModel.setEndTime(endTime.toString());
    }

    eventModel.setCanceled(event.isCanceled());

    return eventModel;
  }
  
  private static LocalDateTime createLocalDate(String date, String time) {
  
    LocalDateTime endLocalDateTime = null;
    
    if (!StringUtils.isEmpty(time) && !StringUtils.isEmpty(date)) {
      String endTimeStr = time.substring(0, time.length() - 2);
      LocalDateTime endLocalTime = LocalDateTime.parse(endTimeStr);
  
      String endDateStr = date.substring(0, date.length() - 2);
      LocalDateTime endLocalDate = LocalDateTime.parse(endDateStr);
  
      endLocalDateTime =
        LocalDateTime.of(
          endLocalDate.getYear(),
          endLocalDate.getMonth(),
          endLocalDate.getDayOfMonth(),
          endLocalTime.getHour() + 1,
          endLocalTime.getMinute());
    }
    
    return endLocalDateTime;
  }
  
  private static Blob createBlobFromString(String imageStr) throws SQLException {
  
    if (!StringUtils.isEmpty(imageStr)) {
      imageStr = imageStr.split(",")[1];
      byte[] bdata = imageStr.getBytes(StandardCharsets.ISO_8859_1);; // From 0
      byte[] decodedByte = Base64.decodeBase64(bdata);
  
      return new SerialBlob(decodedByte);
    }
    
    return null;
  }
  
  private static String createStringFromBlob(Blob blob) throws IOException, SQLException {
    
    if (blob != null) {
      byte[] bdata = blob.getBytes(1, (int) blob.length()); // From 0
      byte[] encodedImage = Base64.encodeBase64(bdata);
  
      return new String(encodedImage, "EUC_KR");
    }
    
    return null;
  }
}
