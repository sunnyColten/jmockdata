package com.github.jsonzou.jmockdata.mocker;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.Mocker;
import com.github.jsonzou.jmockdata.dataconfig.DataSimulator;
import com.github.jsonzou.jmockdata.dataconfig.MockDataRegisterCenter;
import com.github.jsonzou.jmockdata.util.RandomUtils;
import com.github.jsonzou.jmockdata.util.StringUtils;

/**
 * 模拟String对象
 */
public class StringMocker implements Mocker<String> {

  @Override
  public String mock(DataConfig mockConfig,String fieldName) {

    //可以从这里开始改变值的获取方式
    //1.根据正则表达式进行扩展，但测试时需要开发人员对正则表达式熟悉，并且需要进行手动配置某个属性对应怎样的正则表达式。
    //缺点：书写麻烦，并且每个人写法不一样，导致生成数据不规则

    //2.通过注解的方式，需要配置一个测试数据装载库。配置属性数据类型（扩展swagger注解，或者自定义注解），建议在数据库上配置字典将测试数据分类
    //缺点：注解则耦合度高，数据来源需要消耗网络带宽

    //3.通过本地配置好多个数据生成规则形成统一规范，用户使用时只需要指定某个字段对应的数据是什么类型就行，用户也可自定义添加类型
//          Desc annotation = field.getAnnotation(Desc.class);
//          if(annotation != null){

    //查看当前属性是否配置有我们自定义生成方式dataTypeCache
    Object object = Mocker.getObject(mockConfig, fieldName);
    if(object != null){
      return (String)object;
    }

    /**
     * 若根据正则模拟
     */
    if(StringUtils.isNotEmpty(mockConfig.stringRegex())){
      return RandomUtils.nextStringFromRegex(mockConfig.stringRegex());
    }

    int size = RandomUtils.nextSize(mockConfig.sizeRange()[0], mockConfig.sizeRange()[1]);
    String[] stringSeed = mockConfig.stringSeed();
    StringBuilder sb = new StringBuilder(size);
    for (int i = 0; i < size; i++) {
      sb.append(stringSeed[RandomUtils.nextInt(0, stringSeed.length)]);
    }
    return sb.toString();
  }

}
