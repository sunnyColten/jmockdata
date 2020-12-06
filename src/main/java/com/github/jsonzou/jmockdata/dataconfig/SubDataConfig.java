package com.github.jsonzou.jmockdata.dataconfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SubDataConfig {

    private Map<String,String> cache = new ConcurrentHashMap<>();

    //持有对注册类的引用
    private RegisterDataConfig registerDataConfig = new RegisterDataConfig();

    public SubDataConfig(){
        dataCollection(registerDataConfig);
//        logger.info("注册："+this.getClass().getName());
    }

    protected abstract void dataCollection(RegisterDataConfig registerDataConfig);

    /**
     * 获取beanName
     * @return
     */
    protected String getBeanName(){
        Class<? extends SubDataConfig> clazz = this.getClass();
        String clazzName = clazz.getName();
        String beanName = cache.get(clazz);
        if(beanName == null){
            synchronized (SubDataConfig.class){
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
