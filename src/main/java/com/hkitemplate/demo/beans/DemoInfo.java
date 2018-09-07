/**  
* <p>Title: DemoInfo.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年9月5日  
* @version 1.0  
*/  
package com.hkitemplate.demo.beans;

import java.io.Serializable;

import lombok.Data;

/**
 * @author zhanghaow
 *<p>Title: DemoInfo</p> 
 *
 *<p>Description: </p> 
 *
 * 2018年9月5日 上午10:33:10
 */
@Data
public class DemoInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String description, value;

	private long id;

	private String username;

	private String password;

	private String usertype;

	private Integer enabled;

	private String realname;

	private String qq;

	private String email;

	private String tel;

}
