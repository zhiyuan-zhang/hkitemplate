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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * @author zhanghaow
 *<p>Title: DemoInfo</p> 
 *
 *<p>Description: </p> 
 *
 * 2018年9月5日 上午10:33:10
 */

/**
 * @author zhanghaow
 *<p>Title: DemoInfo</p> 
 *
 *<p>Description: </p> 
 *
 * 2018年9月28日 上午11:00:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoInfo implements Serializable{

	private static final long serialVersionUID = 1L;


	private long id;

	private String username;

	private String tel;



}
