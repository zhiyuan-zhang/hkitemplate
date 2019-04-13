//package com.hkitemplate.demo;
//
//import java.util.List;
//
//import com.common.exceptions.CheckException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.alibaba.fastjson.JSON;
//import com.hkitemplate.demo.beans.DemoInfo;
//import com.hkitemplate.demo.dao.DemoInfoMapper;
//import com.hkitemplate.demo.servers.DemoInfoServer;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class HkitemplateApplicationTests {
//
//	@Autowired
//	DemoInfoMapper userinfomapper;
//
//	@Autowired
//	DemoInfoServer infoserver;
//	@Test
//	public void contextLoads() {
//		System.out.println("123123");
//		List<DemoInfo> findById = infoserver.findAll();
//		System.out.println("\n\n");
//		System.out.println("输出结果：" + JSON.toJSONString(findById.get(0)));
//		System.out.println("输出结果：" + JSON.toJSONString(findById));
//		System.out.println("\n\n");
//	}
//	@Test
//	@Transactional
//	public void fixdata() throws Exception{
//
//		infoserver.insert( new DemoInfo(1,"zhangsan","12"));
//
//		infoserver.insert( new DemoInfo(2,"lisi","12"));
//
//		infoserver.insert( new DemoInfo(3,"wangwu","12"));
//		infoserver.insert( new DemoInfo(4,"zhaoliu","12"));
//		infoserver.insert( new DemoInfo(5,"xiaoqi","12"));
//		if(true)
//			throw new CheckException();
//		infoserver.insert( new DemoInfo(6,"qiba","12"));
//		infoserver.insert( new DemoInfo(7,"ten","12"));
//		infoserver.insert( new DemoInfo(8,"ele","12"));
//	}
////	单独调用methodB方法时，因为当前上下文不存在事务，所以会开启一个新的事务。
////	调用methodA方法时，因为当前上下文不存在事务，所以会开启一个新的事务。当执行到methodB时，methodB发现当前上下文有事务，因此就加入到当前事务中来。
//
//	@Transactional(propagation = Propagation.REQUIRED)
//	public void methodA() {
//		methodB();
//		infoserver.insert( new DemoInfo(3,"wangwu","12"));
//		infoserver.insert( new DemoInfo(4,"zhaoliu","12"));
//		infoserver.insert( new DemoInfo(5,"xiaoqi","12"));
//	}
//
//	@Transactional(propagation = Propagation.REQUIRED)
//	public void methodB() {
//		infoserver.insert( new DemoInfo(6,"qiba","12"));
//		infoserver.insert( new DemoInfo(7,"ten","12"));
//		infoserver.insert( new DemoInfo(8,"ele","12"));
//		if(true)
//			throw new CheckException();
//
//	}
//
//
//
//
//
//
//
//
//
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
