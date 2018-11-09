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
public class DemoInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String description;
	private String value;

	private long id;

	private String username;

	private String password;

	private String usertype;

	private Integer enabled;

	private String realname;

	private String qq;

	private String email;

	private String tel;

	/**
	 * 
	 */
	public DemoInfo() {
		super();
	}

	/**
	 * @param description
	 * @param value
	 * @param id
	 * @param username
	 * @param password
	 * @param usertype
	 * @param enabled
	 * @param realname
	 * @param qq
	 * @param email
	 * @param tel
	 */
	public DemoInfo(String description, String value, long id, String username, String password, String usertype,
			Integer enabled, String realname, String qq, String email, String tel) {
		super();
		this.description = description;
		this.value = value;
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.enabled = enabled;
		this.realname = realname;
		this.qq = qq;
		this.email = email;
		this.tel = tel;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}

	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 * @return the enabled
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}


}
