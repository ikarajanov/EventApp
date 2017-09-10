package org.eventapp.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eventapp.utilities.exceptions.UnsupportedDateFormat;
public class DateUtility {

  private static String dateFormat = "dd.MM.YYYY";

  public static Date getDateFromString(String dateString) {
    DateFormat format = new SimpleDateFormat(dateFormat);
    try {
      return format.parse(dateString);
    } catch (ParseException e) {
      throw new UnsupportedDateFormat();
    }
  }

  public static String getStringFromDate(Date date) {

    DateFormat format = new SimpleDateFormat(dateFormat);
    return format.format(date);
  }
}
