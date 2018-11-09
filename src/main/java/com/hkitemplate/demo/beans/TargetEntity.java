package com.hkitemplate.demo.beans;

import java.io.Serializable;

import lombok.Data;
@Data
public class TargetEntity implements Serializable{

	private static final long serialVersionUID = -5723129817918905572L;
	
	private Long id;
	
	private String firstTarget;//一级指标
	
	private String secondTarget;//二级指标
	
	private String thirdTarget;//三级指标
	
	private String fourthTarget;//四级指标
	
	private String description;//备注

	/**
	 * 关联一个计分原则id
	 */
	private Long algorithmId;
	
	private String scoreRule1;//计分原则1
	
	private String scoreRule2;//计分原则2
	
	private String weight;//权重
	
	private String proportion;//比例

}
