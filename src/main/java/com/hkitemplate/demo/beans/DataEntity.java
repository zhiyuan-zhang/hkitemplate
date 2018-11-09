package com.hkitemplate.demo.beans;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class DataEntity implements Serializable{

	private static final long serialVersionUID = -1841004650850968445L;
	
	private Long id;

	private String website;//网站地址
	
	private String category;//新闻分类
	
    private String title;//新闻标题
    
    private String area;//新闻所属板块
    
    private TargetEntity target;//关联指标
    
    private String xz;//新闻性质
    
    private String chongqingArea;//所属重庆的区县
    
    private String content;//新闻内容
    
    private String url;//新闻地址
    
    private String reportTime;//新闻报道时间
    
    private String name;//处理人名字
    
    private Long userId;//关联用户id
    
    private Boolean dealStatus;//处理新闻处理状态 1为已经处理  0 为未处理
    
    private Date updateTime;

	
}
