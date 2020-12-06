package com.github.jsonzou.jmockdata.dataconfig;

import lombok.Data;

@Data
public class PatientInfoDataConfig extends SubDataConfig {

    private static String[] names = {"张三","李四","小明","小红","王五","tony"};
    private static Integer[] ages = {15,22,33,41,34,23};




    @Override
    protected void dataCollection(RegisterDataConfig registerDataConfig) {
        registerDataConfig.registerType("1",names);
        registerDataConfig.registerType("2",ages);
    }
}
