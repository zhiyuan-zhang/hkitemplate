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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hkitemplate.demo.beans.DemoInfo;
import com.hkitemplate.demo.beans.ResultBean;

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

// -------------------------rest 风格的api------------------------------------

	/**
	 * GET（SELECT）：从服务器取出资源（一项或多项）; POST（CREATE）：在服务器新建一个资源;
	 * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）; PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）;
	 * DELETE（DELETE）：从服务器删除资源。
	 * 
	 * 取值 GET @PathVariable POST @RequestParam
	 */
	@RequestMapping(value = { "/demo" }, method = RequestMethod.POST)
	public ResultBean<List<DemoInfo>> addDemoInfo(DemoInfo demo) {
		return null;
	}

	@RequestMapping(value = { "/demo-requestParam" }, method = RequestMethod.POST)
	public ResultBean<Boolean> insertDemoInfo(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("deacription") String description) {
		return null;
	}

	@RequestMapping(value = "/demo/{id}", method = RequestMethod.GET)
	public ResultBean<DemoInfo> findDemoInfo(@PathVariable String id) {
		return null;
	}
	
	@RequestMapping(value = "/demo/{id}", method = RequestMethod.PUT)
	public ResultBean<Boolean> updateDemoInfo(@PathVariable String id ) {
		return null;
	}

	@RequestMapping(value = "/demo/{id}", method = RequestMethod.DELETE)
	private ResultBean<Boolean> deleteDemoteInfo(@PathVariable String id) {
		return null;
	}
	
	@RequestMapping(value = {"/demos", "/"} , method = RequestMethod.GET)
	private ResultBean<List<DemoInfo>> findeAll() {
		return null;
	}
	
// -------------------------url 风格的api------------------------------------
	@PostMapping("/urldemo-add")
	public ResultBean<Long> addDemo(DemoInfo demo) {
		return null;
	}

	@GetMapping("/urldemo-all")
	public ResultBean<List<DemoInfo>> allDemo() {
		return null;

	}

	@PostMapping("/urldemo-delete")
	public ResultBean<Boolean> deleteDemo(long id) {
		return null;
	}

	@PostMapping("/urldemo-update")
	public ResultBean<Boolean> updateDemo(long id) {
		return null;
	}

}
