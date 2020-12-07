package com.github.jsonzou.jmockdata.util.personInfo;


import com.github.jsonzou.jmockdata.util.RandomUtils;
import com.github.jsonzou.jmockdata.util.generator.base.ChineseCharUtils;
import com.github.jsonzou.jmockdata.util.generator.base.GenericGenerator;

public class ChineseAddressGenerator extends GenericGenerator {

    private ChineseAddressGenerator() {
    }


    public static String generate() {
        StringBuilder result = new StringBuilder(genProvinceAndCity());
        result.append(ChineseCharUtils.genRandomLengthChineseChars(2, 3) + "路");
        result.append(RandomUtils.nextInt(1, 8000) + "号");
        result
            .append(ChineseCharUtils.genRandomLengthChineseChars(2, 3) + "小区");
        result.append(RandomUtils.nextInt(1, 20) + "单元");
        result.append(RandomUtils.nextInt(101, 2500) + "室");
        return result.toString();
    }

    private static String genProvinceAndCity() {
        return ChineseAreaList.provinceCityList.get(
            RandomUtils.nextInt(0, ChineseAreaList.provinceCityList.size()));
    }

}
