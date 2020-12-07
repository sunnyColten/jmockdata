package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.Mocker;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public class ClassMocker implements Mocker<Object> {

  private Class clazz;

  private Type[] genericTypes;

  ClassMocker(Class clazz, Type[] genericTypes) {
    this.clazz = clazz;
    this.genericTypes = genericTypes;
  }

  /**
   *  a.isAssignableFrom(b.clazz)
   *     a对象所对应类信息是b对象所对应的类信息的父类或者是父接口，简单理解即a是b的父类或接口
   *     a对象所对应类信息与b对象所对应的类信息相同，简单理解即a和b为同一个类或同一个接口
   *
   * 如果不是数组、 Map、集合、枚举 -->则通过BeanMocker处理
   *
   * ClassMock交由Spirng管理
   *
   * @param mockConfig 模拟数据配置
   * @return
   */
  @Override
  public Object mock(DataConfig mockConfig,String fieldName) {
    Mocker mocker;
    if (clazz.isArray()) {
      mocker = new ArrayMocker(clazz);
    } else if (Map.class.isAssignableFrom(clazz)) {
      mocker = new MapMocker(genericTypes);
    } else if (Collection.class.isAssignableFrom(clazz)) {
      mocker = new CollectionMocker(clazz, genericTypes[0]);
    } else if (clazz.isEnum()) {
      mocker = new EnumMocker(clazz);
    } else {
      mocker = mockConfig.globalConfig().getMocker(clazz);
      if (mocker == null) {
        mocker = new BeanMocker(clazz);
      }
    }
    return mocker.mock(mockConfig, fieldName);
  }

}
