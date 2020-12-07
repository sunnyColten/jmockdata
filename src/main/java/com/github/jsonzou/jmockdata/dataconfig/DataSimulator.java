package com.github.jsonzou.jmockdata.dataconfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DataSimulator {

    private Map<String,String> cache = new ConcurrentHashMap<>();

    //持有对注册类的引用
    private MockDataRegisterCenter mockDataRegisterCenter = new MockDataRegisterCenter();

    public DataSimulator(){
//        log.debug("*************完成了{}注册*************", getBeanName());
        System.out.println("*************完成了"+getBeanName()+"注册*************");
        registerAllType();
    }

    /**
     * 往注册器中注册类型
     * @param
     */
    protected abstract void registerAllType();

    protected final void register(String ... registerType){
        for (String currType : registerType) {
            System.out.println("注册了类型"+currType);
//            log.debug("注册了类型", currType);
            mockDataRegisterCenter.registerType(currType,this);
        }
    }

    /**
     * 数据返回方式由不同的实现类自己决定
     * 不同的类型返回方式由实现类决定
     *     可以从写死的数组串中中返回
     *        String[] xxxx = {"血糖","血脂","肾功","胸透","心电图","血尿常规"};
     *        return xxxx[random.xxx]
     *     也可以根据工具类生成
     *        return xxxxUitls.generatexxxx
     *
     * @param dataType
     * @return
     */
    public abstract Object getObject(String dataType);

    /**
     * 获取beanName
     * @return
     */
    protected String getBeanName(){
        Class<? extends DataSimulator> clazz = this.getClass();
        String clazzName = clazz.getName();
        String beanName = cache.get(clazz);
        if(beanName == null){
            synchronized (DataSimulator.class){
                if(cache.get(clazz) == null){
                    String substring = clazzName.substring(clazzName.lastIndexOf(".")+1);
                    beanName = substring.substring(0,1).toLowerCase() + substring.substring(1);
                    cache.put(clazzName,beanName);
                }
            }
        }
        return beanName;
    }


    /**
     * 从配置文件中获取配置 key(beanName):value(dataType)
     * @return
     */
    protected String[] getType(){
        String beanName = getBeanName();
        // TODO

        return null;
    }
}
