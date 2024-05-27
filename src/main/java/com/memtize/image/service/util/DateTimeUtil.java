package com.memtize.image.service.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateTimeUtil {

  private DateTimeUtil() {}

  public static final DateTimeFormatter DEFAULT_DT_PATTERN =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  public static final DateTimeFormatter DEFAULT_T_PATTERN = DateTimeFormatter.ofPattern("HH:mm:ss");
  public static final DateTimeFormatter DEFAULT_D_PATTERN =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static LocalDate parseDate(String date) {
    DateTimeFormatterBuilder dateTimeFormatterBuilder =
        new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("[yyyy-MM-dd]" + "[dd.MM.yyyy]" + "[d.MM.yyyy]" + "[d.M.yyyy]" + "[dd.M.yyyy]"));
    DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
    return LocalDate.parse(date, dateTimeFormatter);
  }
}
