package com.hkitemplate.demo.beans;

/**
 * @author durantjiang
 * 常量类
 */
public class Const {

    /**
     * 关于状态定义
     */
    public interface Status{
        Integer ONE = 1;
        Integer TWO = 2;
        Integer THREE = 3;
        Integer SEVEN = 7;
        Integer EIGHT = 8;
        Integer TEN = 10;
        Integer FOURNINE = 49;
        Integer FIFTY = 50;
    }

    /**
     * 关于角色定义
     */
    public interface Role{
        String USER_ONE = "user1";
    }

    /**
     * 关于类型
     */
    public interface Type{
        String BUSINESS = "金融企业";
        String TOTAL = "total";
        String A = "A";
        String B = "B";
    }

}