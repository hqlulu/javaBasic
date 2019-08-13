package com.lhq.basic;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hqlulu
 * @date 2019/8/13 下午8:14
 */
public class MyBeanFactoryImpl implements MyBeanFactory {

    private static ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<String, Object>();
    private static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private static Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());

    public Object getBeanByName(String name) throws Exception {
        Object object = beanMap.get(name);
        if (null != object){
            return object;
        }
        object = getObject(beanDefinitionMap.get(name));
        if (null != object){

        }
        return null;
    }

    public void setFild(Object bean) throws Exception{
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field: declaredFields){
            String fieldAllName = field.getType().getName();
            if (beanNameSet.contains(fieldAllName)){
                Object findBean = getBeanByName(fieldAllName);
                field.setAccessible(true);
                field.set(bean, findBean);
            }
        }
    }

    public Object getObject(BeanDefinition beanDefinition) throws Exception{
        String className = beanDefinition.getClassName();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        }catch (ClassNotFoundException e){
            throw new Exception("not found "+className);
        }
        return clazz;
    }

    public static void setBeanDineMap(ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap){
        MyBeanFactoryImpl.beanDefinitionMap = beanDefinitionMap;
    }

    public static void setBeanNameSet(Set<String> beanNameSet){
        MyBeanFactoryImpl.setBeanNameSet(beanNameSet);
    }
}
