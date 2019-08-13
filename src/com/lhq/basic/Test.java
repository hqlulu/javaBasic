package com.lhq.basic;

import org.springframework.boot.SpringApplication;

/**
 * @author hqlulu
 * @date 2019/8/13 上午8:00
 */
public class Test {

    public static void main(String[] args) throws Exception {

        System.out.print("test");
        SpringApplication.run(Test.class, args);

        MyBeanFactoryImpl beanFactory = new MyBeanFactoryImpl();
        User user1 = (User)beanFactory.getBeanByName("com.lhq.basic.User");
        Student student = user1.getStudent();
        System.out.println(user1);
        System.out.println(student);
    }
}
