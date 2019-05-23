package com.hkitemplate.demo.beans;

/**
 *
 * 功能描述: 出席参数
 *
 * @auther: hki
 * @date: 2019-05-20 19:43
 */
public enum AttendEnum {

   
    /**
     * 0
     */
    sys(0),
    /**
     * 1
     */
    accept(1),
    /**
     * 2
     */
    reject(2);





    private Integer action;

    AttendEnum(Integer action) {
        this.action = action;
    }

    public Integer getAction() {
        return action;
    }



}
