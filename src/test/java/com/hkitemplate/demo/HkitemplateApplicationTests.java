package com.hkitemplate.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.hkitemplate.demo.beans.DemoInfo;
import com.hkitemplate.demo.dao.DemoInfoMapper;
import com.hkitemplate.demo.servers.DemoInfoServer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HkitemplateApplicationTests {

	@Autowired
	DemoInfoMapper userinfomapper;
	
	@Autowired
	DemoInfoServer infoserver;
	@Test
	public void contextLoads() {
		System.out.println("123123");
		List<DemoInfo> findById = infoserver.findById(1);
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + JSON.toJSONString(findById.get(0)));
		System.out.println("输出结果：" + JSON.toJSONString(findById));
		System.out.println("\n\n\n\n\n");
	}

}
