package com.github.jsonzou.jmockdata.dataconfig;


import lombok.Data;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟数据注册中心
 */
@Data
public class MockDataRegisterCenter {
    protected static Map<String, Object> dataMap = new ConcurrentHashMap<>();

    public void registerType(String dataType,Object bean){
        dataMap.put(dataType,bean);
    }

    public static Object dataSimulator(String dataType){
        return dataMap.get(dataType);
    }

}
