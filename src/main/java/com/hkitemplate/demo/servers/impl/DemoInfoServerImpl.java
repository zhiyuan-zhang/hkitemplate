/**  
* <p>Title: DemoInfoServerImpl.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年9月8日  
* @version 1.0  
*/
package com.hkitemplate.demo.servers.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkitemplate.demo.beans.DemoInfo;
import com.hkitemplate.demo.dao.DemoInfoMapper;
import com.hkitemplate.demo.servers.DemoInfoServer;

/**
 * @author zhanghaow
 *         <p>
 *         Title: DemoInfoServerImpl
 *         </p>
 *
 *         <p>
 *         Description:
 *         </p>
 *
 *         2018年9月8日 下午2:17:30
 */
@Service
public class DemoInfoServerImpl implements DemoInfoServer {
	private static final Logger logger = LoggerFactory.getLogger(DemoInfoServerImpl.class);

	@Autowired
	DemoInfoMapper demoInfoMapper;

	@Override
	public List<DemoInfo> findByName(String name) {
		// check()
		// 校验通过后打印重要的日志
		logger.info("findByName:" + name);
		List<DemoInfo> finddemo = demoInfoMapper.findByName(name);
		// 修改操作需要打印操作结果
		logger.info("findByName success, id:{}", finddemo);
		return finddemo;
	}

	@Override
	public List<DemoInfo> findById(int id) {

		// check()
		// 校验通过后打印重要的日志
		logger.info("findById:" + id);
		List<DemoInfo> finddemo = demoInfoMapper.findById(id);
		// 修改操作需要打印操作结果
		logger.info("findById success, id:{}", finddemo);
		return finddemo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hkitemplate.demo.server.DemoInfoServer#insert(com.hkitemplate.demo.beans.
	 * DemoInfo)
	 */
	@Override
	public Boolean insert(DemoInfo id) {
		// check()
		// 校验通过后打印重要的日志
		logger.info("insert  :" + id);
		int insert = demoInfoMapper.insert(id);
		// 修改操作需要打印操作结果
		logger.info("insert   success, id:{}", insert);
		return insert==0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hkitemplate.demo.server.DemoInfoServer#insertByMap(java.util.Map)
	 */
	@Override
	public Boolean insertByMap(Map<String, Object> map) {
		// check()
		// 校验通过后打印重要的日志
		logger.info("insertByMap  :" + map);
		int insertByMap = demoInfoMapper.insertByMap(map);
		// 修改操作需要打印操作结果
		logger.info("insertByMap   success, id:{}", insertByMap);
		return insertByMap== 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hkitemplate.demo.server.DemoInfoServer#findAll()
	 */
	@Override
	public List<DemoInfo> findAll() {

		// check()
		// 校验通过后打印重要的日志
		logger.info("findAll ()" );
		List<DemoInfo> finddemo = demoInfoMapper.findAll();
		// 修改操作需要打印操作结果
		logger.info("findAll success, id:{}", finddemo);
		return finddemo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hkitemplate.demo.server.DemoInfoServer#update(com.hkitemplate.demo.beans.
	 * DemoInfo)
	 */
	@Override
	public Boolean update(DemoInfo ui) {
		// check()
		// checkPermission()
		// 校验通过后打印重要的日志
		logger.info("update config:" );
		demoInfoMapper.update(ui);
		// 修改操作需要打印操作结果
		logger.info("update success, id:{}");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hkitemplate.demo.server.DemoInfoServer#delete(int)
	 */
	@Override
	public Boolean delete(int id) {
		// check()
		// checkPermission()
		// 校验通过后打印重要的日志
		logger.info("delete config" );
		demoInfoMapper.delete(id);
		// 修改操作需要打印操作结果
		logger.info("delete success");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hkitemplate.demo.server.DemoInfoServer#queryById()
	 */
	@Override
	public List<DemoInfo> queryByConditions(String conditions){
		// check()
		// checkPermission()
		// 校验通过后打印重要的日志
		logger.info("queryByConditions config" );
//		demoInfoMapper.delete(id);
		// 修改操作需要打印操作结果
		logger.info("queryByConditions success");
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hkitemplate.demo.servers.DemoInfoServer#queryById()
	 * 输出特定的几个字段
	 */
	@Override
	public List<DemoInfo> queryById() {
		// check()
		// checkPermission()
		// 校验通过后打印重要的日志
		logger.info("queryById config" );
//		demoInfoMapper.delete(id);
		// 修改操作需要打印操作结果
		logger.info("queryById success");
		return null;
	}

}
