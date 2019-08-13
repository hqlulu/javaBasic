package com.lhq.basic;

import lombok.Data;

/**
 * @author hqlulu
 * @date 2019/8/13 下午8:30
 */

@MyIoc
public class Student {
    public String play(){
        return "student "+toString();
    }
}
