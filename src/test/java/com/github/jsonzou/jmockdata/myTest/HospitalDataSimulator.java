package com.github.jsonzou.jmockdata.myTest;

import com.github.jsonzou.jmockdata.dataconfig.DataSimulator;
import java.util.Random;

public class HospitalDataSimulator extends DataSimulator {

    private static String[] hospitalNames = {"血糖","血脂","肾功","胸透","心电图","血尿常规"};
    private static String[] projects = {"中国医学科学院北京协和医院","四川大学华西医院","中国人民解放军总医院","上海交通大学医学院附属瑞金医院","复旦大学附属中山医院","中山大学附属第一医院"};

    @Override
    public void registerAllType() {
        register("3","4");
    }

    /**
     * 数据返回方式由不同的实现类自己决定
     */
    @Override
    public Object getObject(String dataType){
        if(dataType.equals("3")){
            Random rand = new Random();
            return projects[rand.nextInt(projects.length)];
        }else if(dataType.equals("4")){
            Random rand = new Random();
            return hospitalNames[rand.nextInt(hospitalNames.length)];
        }
        return null;
    }
}
