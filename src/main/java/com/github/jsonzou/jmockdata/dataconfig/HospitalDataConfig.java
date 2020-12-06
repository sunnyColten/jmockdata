package com.github.jsonzou.jmockdata.dataconfig;

public class HospitalDataConfig extends SubDataConfig {

    private static String[] projects = {"血糖","血脂","肾功","胸透","心电图","血尿常规"};
    private static String[] hospitalNames = {"中国医学科学院北京协和医院","四川大学华西医院","中国人民解放军总医院","上海交通大学医学院附属瑞金医院","复旦大学附属中山医院","中山大学附属第一医院"};

    @Override
    protected void dataCollection(RegisterDataConfig registerDataConfig) {
        registerDataConfig.registerType("3",hospitalNames);
        registerDataConfig.registerType("4",projects);
    }
}
