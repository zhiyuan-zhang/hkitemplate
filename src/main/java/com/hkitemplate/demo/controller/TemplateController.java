/**  
* <p>Title: TemplateController.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年9月5日  
* @version 1.0  
*/
package com.hkitemplate.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.exceptions.CheckException;
import com.hkitemplate.demo.beans.DemoInfo;
import com.hkitemplate.demo.beans.ResultBean;
import com.hkitemplate.demo.servers.DemoInfoServer;

/**
 * @author zhanghaow
 *         <p>
 *         Title: TemplateController
 *         </p>
 *
 *         <p>
 *         Description: 下面有两种风格一种是rest风格的api 一种是以前的url 风格的api
 *         </p>
 *
 *         2018年9月5日 上午10:49:36
 */
@RestController
@RequestMapping("/template")
public class TemplateController {

//	@Resource
	@Autowired
	private DemoInfoServer infoserver;

// -------------------------rest 风格的api------------------------------------

	/**
	 * GET（SELECT）：从服务器取出资源（一项或多项）; POST（CREATE）：在服务器新建一个资源;
	 * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）; PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）;
	 * DELETE（DELETE）：从服务器删除资源。
	 * 
	 * 取值 GET @PathVariable POST @RequestParam
	 */
	@RequestMapping(value = { "/demo" }, method = RequestMethod.POST)
	public ResultBean<Boolean> addDemoInfo(DemoInfo demo) {
		return new ResultBean<Boolean>(infoserver.insert(demo));
	}

	@RequestMapping(value = { "/demo-requestParam" }, method = RequestMethod.POST)
	public ResultBean<Boolean> insertDemoInfo(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("deacription") String description) {
		// return new ResultBean<Boolean> (infoserver.insert(new DemoInfo(description,
		// description, 0, name, description, description, null, description,
		// description, description, description)));
		DemoInfo demoInfo = new DemoInfo(id,name,0, description, description, description, null, description, description, description, description);
		
		return new ResultBean<Boolean>(infoserver.insert(demoInfo));
	}

	@RequestMapping(value = "/demo/{id}", method = RequestMethod.GET)
	public ResultBean<List<DemoInfo>> findDemoInfo(@PathVariable("id") int id) {
		return new ResultBean<List<DemoInfo>>(infoserver.findById(id));
	}

	@RequestMapping(value = "/demo/{id}", method = RequestMethod.PUT)
	public ResultBean<Boolean> updateDemoInfo(@PathVariable DemoInfo di) {
		return new ResultBean<Boolean>(infoserver.update(di));
	}

	@RequestMapping(value = "/demo/{id}", method = RequestMethod.DELETE)
	public ResultBean<Boolean> deleteDemoInfo(@PathVariable("id") int id) {
		return new ResultBean<Boolean>(infoserver.delete(id));
	}

	@RequestMapping(value = { "/demos", "/" }, method = RequestMethod.GET)
	private ResultBean<List<DemoInfo>> findeAll() {
		return new ResultBean<List<DemoInfo>>(infoserver.findAll());
	}

// -------------------------url 风格的api------------------------------------
	@PostMapping("/urldemo-add")
	public ResultBean<Boolean> addDemo(DemoInfo demo) {

		return new ResultBean<Boolean>(infoserver.insert(demo));
	}
	@GetMapping(value = "/urldemo-all")
	public ResultBean<List<DemoInfo>> allDemo() {
		return new ResultBean<List<DemoInfo>>(infoserver.findAll());
	}

	@PostMapping("/urldemo-delete")
	public ResultBean<Boolean> deleteDemo(int id) {
		return new ResultBean<Boolean>(infoserver.delete(id));
	}

	@PostMapping("/urldemo-update")
	public ResultBean<Boolean> updateDemo(DemoInfo demo) {
		return new ResultBean<Boolean>(infoserver.update(demo));
	}

}
