package com.lhq.basic;

/**
 * @author hqlulu
 * @date 2019/8/13 下午8:13
 */
public interface MyBeanFactory {
    Object getBeanByName(String name) throws Exception;
}
