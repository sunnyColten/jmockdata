package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.Mocker;
import com.github.jsonzou.jmockdata.util.RandomUtils;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigDecimal对象模拟器
 */
public class BigDecimalMocker implements Mocker<BigDecimal> {

  @Override
  public BigDecimal mock(DataConfig mockConfig,String fieldName) {

    Object object = Mocker.getObject(mockConfig, fieldName);
    if(object != null){
      return (BigDecimal)object;
    }
    return BigDecimal.valueOf(mockConfig.globalConfig().getMocker(Double.class).mock(mockConfig, fieldName));
  }

}
