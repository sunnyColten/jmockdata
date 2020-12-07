package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.Mocker;
import com.github.jsonzou.jmockdata.dataconfig.DataSimulator;
import com.github.jsonzou.jmockdata.dataconfig.MockDataRegisterCenter;
import com.github.jsonzou.jmockdata.util.RandomUtils;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟Map
 */
public class MapMocker implements Mocker<Object> {

  private Type[] types;

  MapMocker(Type[] types) {
    this.types = types;
  }

  @Override
  public Object mock(DataConfig mockConfig,String fieldName) {
    //查看当前属性是否配置有我们自定义生成方式dataTypeCache
    String keyDataType = null;
    String valueDataType = null;
    if(fieldName != null && !fieldName.equals("")){
      keyDataType = mockConfig.dataType(fieldName+"@Key");
      valueDataType = mockConfig.dataType(fieldName+"@Value");
    }


    int size = RandomUtils.nextSize(mockConfig.sizeRange()[0], mockConfig.sizeRange()[1]);
    Map<Object, Object> result = new HashMap<>(size);
    BaseMocker keyMocker = new BaseMocker(types[0]);
    BaseMocker valueMocker = new BaseMocker(types[1]);
    for (int index = 0; index < size; index++) {
      Object key = null;
      Object value = null;
      if(keyDataType != null && !keyDataType.equals("")){
        DataSimulator dataSimulator = (DataSimulator) MockDataRegisterCenter.dataSimulator(keyDataType);
        if(dataSimulator != null){
          key = dataSimulator.getObject(keyDataType);
        }
      }else{
        key = keyMocker.mock(mockConfig, fieldName);
      }
      //获取value
      if(valueDataType != null && !valueDataType.equals("")){
        DataSimulator dataSimulator = (DataSimulator) MockDataRegisterCenter.dataSimulator(valueDataType);
        if(dataSimulator != null){
          value = dataSimulator.getObject(valueDataType);
        }
      }else{
        value = valueMocker.mock(mockConfig, fieldName);
      }
      result.put(key,value);
    }
    return result;
  }
}
