package com.github.jsonzou.jmockdata.util.personInfo;


import com.github.jsonzou.jmockdata.util.generator.base.GenericGenerator;
import org.apache.commons.lang3.RandomStringUtils;

public class EmailAddressGenerator extends GenericGenerator {

    private EmailAddressGenerator() {
    }


    public static String generate() {
        StringBuilder result = new StringBuilder();
        result.append(RandomStringUtils.randomAlphanumeric(10));
        result.append("@");
        result.append(RandomStringUtils.randomAlphanumeric(5));
        result.append(".");
        result.append(RandomStringUtils.randomAlphanumeric(3));

        return result.toString().toLowerCase();
    }
}
