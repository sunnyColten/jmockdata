package com.github.jsonzou.jmockdata;

import com.github.jsonzou.jmockdata.dataconfig.DataSimulator;
import com.github.jsonzou.jmockdata.dataconfig.MockDataRegisterCenter;

/**
 * 模拟器接口
 */
public interface Mocker<T> {

  /**
   * 模拟数据
   *
   * @param mockConfig 模拟数据配置
   * @return 模拟数据对象
   */
  T mock(DataConfig mockConfig,String fieldName);

  static Object getObject(DataConfig mockConfig,String fieldName){
    //查看当前属性是否配置有我们自定义生成方式dataTypeCache
    String dataType = mockConfig.dataType(fieldName);
    if(dataType != null && !dataType.equals("")){
      //使用手动加载的测试数据，更接近真实数据
      DataSimulator dataSimulator = (DataSimulator) MockDataRegisterCenter.dataSimulator(dataType);
      if(dataSimulator != null){
        //这里需要判断是否是List、Map类型，如果是则使用对应的生成方式
        //但是List<T>,Map<K,V>。T,K,V的生成方式由我们决定
        return dataSimulator.getObject(dataType);
      }
    }
    return null;
  }

}
