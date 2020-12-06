package com.github.jsonzou.jmockdata.myTest;


import com.github.jsonzou.jmockdata.annotation.Desc;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Person {
    private String name;
    private Integer age;
    private String hospitalName;
    private String project;

}
