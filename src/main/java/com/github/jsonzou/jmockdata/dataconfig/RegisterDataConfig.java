package com.github.jsonzou.jmockdata.dataconfig;


import lombok.Data;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class RegisterDataConfig {
//    private Map<String,Object> typeToMap = new HashMap();
    protected static Map<String, Object[]> dataMap = new ConcurrentHashMap<>();

    public void registerType(String dataType,Object[] data){
        dataMap.put(dataType,data);
    }

    public static Object getObject(String dataType){
        Random rand = new Random();
        Object[] objects = dataMap.get(dataType);
        return dataMap.get(dataType)[rand.nextInt(objects.length)];
    }
}
