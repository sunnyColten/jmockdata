package com.github.jsonzou.jmockdata.util.generator.base;

import java.util.Date;
import java.util.Random;

public abstract class GenericGenerator {
//    public static abstract String generate();

    private static Random random  = new Random(new Date().getTime());

    protected static Random getRandomInstance() {
//        if (random == null) {
//            random = new Random(new Date().getTime());
//        }
        return random;
    }
}
