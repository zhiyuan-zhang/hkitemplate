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

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "DemoInfoServer")
public interface DemoInfoServer {
	@Cacheable(keyGenerator="accountKeyGenerator")
	List<DemoInfo> findByName(String name);
	@Cacheable(keyGenerator="accountKeyGenerator")
	List<DemoInfo> findById(int id);
	
	@CachePut(keyGenerator="accountKeyGenerator")
	Boolean insert(DemoInfo id);
	
	@CachePut(keyGenerator="accountKeyGenerator")
	Boolean insertByMap(Map<String, Object> map);

	@Cacheable(keyGenerator="accountKeyGenerator")
	List<DemoInfo> findAll();
	
	@CachePut(keyGenerator="accountKeyGenerator")
	Boolean update(DemoInfo ui);
	@CacheEvict(keyGenerator="accountKeyGenerator")
	Boolean delete(int id);
	@Cacheable(keyGenerator="accountKeyGenerator")
	List<DemoInfo> queryById();

	List<DemoInfo> queryByConditions(String conditions);

}
