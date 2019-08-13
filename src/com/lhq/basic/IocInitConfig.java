package com.lhq.basic;

import org.reflections.Reflections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hqlulu
 * @date 2019/8/13 下午8:01
 */
@Component
@Order(value=1)
public class IocInitConfig implements CommandLineRunner {

    public void run(String... strings) throws Exception {
        ConcurrentHashMap<String, BeanDefinition> concurrentHashMap = new ConcurrentHashMap<String, BeanDefinition>();
        Reflections reflections = new Reflections();
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(MyIoc.class);
        for (Class clazz : typesAnnotatedWith){
            BeanDefinition beanDefinition = new BeanDefinition();
            String className = clazz.getName();
            String superClassName = clazz.getSuperclass().getName();
            beanDefinition.setClassName(className);
            beanDefinition.setSuperNames(superClassName);
            beanDefinition.setAlias(getClassName(className));
            concurrentHashMap.put(className, beanDefinition);
        }
        MyBeanFactoryImpl.setBeanDineMap(concurrentHashMap);
    }

    private String getClassName(String beanClassName){
        String className = beanClassName.substring(beanClassName.lastIndexOf(".") + 1);
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        return className;
    }
}
