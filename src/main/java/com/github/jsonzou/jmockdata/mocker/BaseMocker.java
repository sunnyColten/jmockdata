package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.Mocker;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

@SuppressWarnings("unchecked")
public class BaseMocker<T> implements Mocker<T> {

  private Type type;

  private Type[] genericTypes;

  public BaseMocker(Type type, Type... genericTypes) {
    this.type = type;
    this.genericTypes = genericTypes;
  }

  /**
   * 如果不是参数化类型、泛型数组类型、泛型的类型变量
   *      则通过对应的ClassMocker处理
   * @param mockConfig 模拟数据配置
   * @return
   */
  @Override
  public T mock(DataConfig mockConfig) {
    Mocker mocker;
    //参数化类型，如 Collection<String>  例如List<T>、Set<T>等
    if (type instanceof ParameterizedType) {
      mocker = new GenericMocker((ParameterizedType) type);
    } else if (type instanceof GenericArrayType) {//泛型数组类型，例如List<String>[] 、T[]等
      mocker = new ArrayMocker(type);
    } else if (type instanceof TypeVariable) {//泛型的类型变量，指的是List<T>、Map<K,V>中的T，K，V等值，
      mocker = new BaseMocker(mockConfig.globalConfig().getVariableType(((TypeVariable) type).getName()));
    } else {
      mocker = new ClassMocker((Class) type, genericTypes);
    }
    return (T) mocker.mock(mockConfig);
  }
}
