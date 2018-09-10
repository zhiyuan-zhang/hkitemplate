/**  
* <p>Title: DemoInfoServer.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年9月8日  
* @version 1.0  
*/
package com.hkitemplate.demo.servers;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hkitemplate.demo.beans.DemoInfo;

/**
 * @author zhanghaow
 *         <p>
 *         Title: DemoInfoServer
 *         </p>
 *
 *         <p>
 *         Description:
 *         </p>
 *
 *         2018年9月8日 下午2:15:21
 */
@Service
public interface DemoInfoServer {

	List<DemoInfo> findByName(String name);

	List<DemoInfo> findById(int id);

	int insert(DemoInfo id);

	int insertByMap(Map<String, Object> map);

	List<DemoInfo> findAll();

	Boolean update(DemoInfo ui);

	Boolean delete(int id);

	List<DemoInfo> queryById();

	List<DemoInfo> queryByConditions(String conditions);

}
