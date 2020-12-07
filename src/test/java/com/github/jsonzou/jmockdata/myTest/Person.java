package com.github.jsonzou.jmockdata.myTest;


import com.github.jsonzou.jmockdata.annotation.MockIgnore;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Person {
    @MockIgnore
    private String name;
    private Integer age;
    private String hospitalName;
    private List<String> projectList;
    private String[] array;
    private Map<Integer,String> hashMap;
}
