/**  
* <p>Title: UserInfoMapper.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年9月7日  
* @version 1.0  
*/
package com.hkitemplate.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hkitemplate.demo.beans.DemoInfo;

/**
 * @author zhanghaow
 *         <p>
 *         Title: UserInfoMapper
 *         </p>
 *
 *         <p>
 *         Description:
 *         </p>
 *
 *         2018年9月7日 下午2:17:20
 */
@Mapper
public interface DemoInfoMapper {

	@Select("SELECT * FROM demo_info WHERE username = #{name}")
	List<DemoInfo> findByName(@Param("name") String name);

	@Select("SELECT * FROM demo_info WHERE id = #{id}")
	List<DemoInfo> findById(@Param("id") int id);

	@Insert("INSERT INTO demo_info(Id, username, password, usertype, enabled, realname, email, qq, tel, description, value) VALUES(#{Id}, #{username}, #{password}, #{usertype}, #{enabled}, #{realname}, #{email}, #{qq}, #{tel}, ${description}, #{value}")
	int insert(DemoInfo id);

	// 两个语句实现效果一致
	// @Insert("INSERT INTO
	// demo_info(Id,username,password,usertype,enabled,realname,email,tel)
	// VALUES(#{id,jdbcType=INTEGER},
	// #{username,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},
	// #{usertype,jdbcType=VARCHAR},#{enabled,jdbcType=INTEGER},
	// #{realname,jdbcType=VARCHAR},#{email,jdbcType=VARCHAR},
	// #{tel,jdbcType=VARCHAR})")
	@Insert("INSERT INTO demo_info(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id}, #{username},#{password}, #{usertype},#{enabled}, #{realname},#{email}, #{tel})")
	int insertByMap(Map<String, Object> map);

	@Select("SELECT * FROM demo_info WHERE 1=1 ")
	List<DemoInfo> findAll();

	@Update("UPDATE demo_info SET password=#{password} WHERE username=#{username}")
	void update(DemoInfo ui);

	@Delete("DELETE FROM demo_info WHERE id =#{id}")
	void delete(int id);

	@Results({ @Result(property = "username", column = "username"),
			@Result(property = "realname", column = "realname") })
	@Select("SELECT username,realname FROM demo_info WHERE 1=1")
	List<DemoInfo> queryById();

}
