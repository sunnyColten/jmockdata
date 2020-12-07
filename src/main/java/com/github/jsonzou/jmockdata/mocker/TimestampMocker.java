package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.Mocker;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * Timestamp对象模拟器
 */
public class TimestampMocker implements Mocker<Timestamp> {
  private DateMocker dateMocker = new DateMocker();
  @Override
  public Timestamp mock(DataConfig mockConfig,String fieldName) {
    Object object = Mocker.getObject(mockConfig, fieldName);
    if(object != null){
      return (Timestamp)object;
    }

     Date date = dateMocker.mock(mockConfig, fieldName);
     return Timestamp.from(Instant.ofEpochMilli(date.getTime()));
  }
}
