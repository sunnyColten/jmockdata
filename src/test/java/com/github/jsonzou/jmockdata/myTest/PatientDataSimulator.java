package com.github.jsonzou.jmockdata.myTest;

import com.github.jsonzou.jmockdata.dataconfig.DataSimulator;
import com.github.jsonzou.jmockdata.util.personInfo.ChineseNameGenerator;
import java.util.Random;
import lombok.Data;

@Data
public class PatientDataSimulator extends DataSimulator {

    private static String[] names = {"张三","李四","小明","小红","王五","tony"};
    private static Integer[] ages = {15,22,33,41,34,23};

    @Override
    public void registerAllType() {
        register("1","2");
    }

    @Override
    public Object getObject(String dataType) {
        if(dataType.equals("1")){
            //从该类中写死的数据中随机获取 人名name
//            Random rand = new Random();
//            return names[rand.nextInt(names.length)];
            //从工具类中获取 人名name
            return ChineseNameGenerator.generate();
        }else if(dataType.equals("2")){
            Random rand = new Random();
            return ages[rand.nextInt(ages.length)];
        }
        return null;
    }

}
