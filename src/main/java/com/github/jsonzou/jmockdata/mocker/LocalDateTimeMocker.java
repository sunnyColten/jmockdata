package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.Mocker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * LocalDateTime对象模拟器
 */
public class LocalDateTimeMocker implements Mocker<LocalDateTime> {
  private DateMocker dateMocker = new DateMocker();
  @Override
  public LocalDateTime mock(DataConfig mockConfig,String fieldName) {
    Object object = Mocker.getObject(mockConfig, fieldName);
    if(object != null){
      return (LocalDateTime)object;
    }

     Date date = dateMocker.mock(mockConfig,fieldName);
     return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
  }
}
