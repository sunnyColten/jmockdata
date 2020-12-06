package com.github.jsonzou.jmockdata.myTest;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.annotation.Desc;
import com.github.jsonzou.jmockdata.bean.enums.ErrorEnum;
import com.github.jsonzou.jmockdata.dataconfig.HospitalDataConfig;
import com.github.jsonzou.jmockdata.dataconfig.PatientInfoDataConfig;
import com.github.jsonzou.jmockdata.util.ReflectionUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class JMockDataTest {

    @Test
    public void test(){
        // TODO PatientInfoDataConfig等配置后面统一交给Spring容器管理，非手动创建
        //病人相关属性测试数据统一配置
        PatientInfoDataConfig patientInfoDataConfig = new PatientInfoDataConfig();
        // TODO HospitalDataConfig等配置后面统一交给Spring容器管理，非手动创建
        //医院相关属性测试数据统一配置
        HospitalDataConfig hospitalDataConfig = new HospitalDataConfig();
        MockConfig mockConfig = new MockConfig()
                .subConfig(Person.class, "name-1", "age-2", "hospitalName-3", "project-4")
                .globalConfig();
        int i = 10;
        while(i != 0){
            System.out.println(JMockData.mock(Person.class, mockConfig));
            i--;
        }
    }

    @Test
    public void test2(){
        List<String> list = new LinkedList();
        Class<? extends List> clazz = list.getClass();
        System.out.println(clazz.getName());
        System.out.println(clazz.getTypeName());

        //获取接口类型信息
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces)
            System.out.println("Generic Interface：" + genericInterface.getTypeName());

        //打印Type的具体类型
        for (Type genericInterface : genericInterfaces)
            System.out.println("Type Class：" + genericInterface.getClass().getName());

        //获取父类类型信息
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println("Generic Superclass：" + genericSuperclass.getTypeName());
    }

    @Test
    public void test3() throws IllegalAccessException {
        Person test = new Person();
        Class<Person> personClass = (Class<Person>) test.getClass();
        //获取属性上的注解
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Desc annotation = field.getAnnotation(Desc.class);
            System.out.println(annotation.remark());
            System.out.println(annotation.value());
        }


    }

    @Test
    public void test4() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        Object string = "测试姓名";
        System.out.println(person);
        ReflectionUtils.setFieldValue(person, name, string);
        System.out.println(person);

    }

    @Test
    public void test5() throws NoSuchFieldException, IllegalAccessException {
        PatientInfoDataConfig patientInfoDataConfig = new PatientInfoDataConfig();
        System.out.println(patientInfoDataConfig);
        HospitalDataConfig hospitalDataConfig = new HospitalDataConfig();
        System.out.println(hospitalDataConfig);
    }

}
