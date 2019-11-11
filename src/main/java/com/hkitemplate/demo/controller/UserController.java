package com.hkitemplate.demo.controller;


import com.hkitemplate.demo.beans.ResultBean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiParam;

import com.hkitemplate.demo.mvc.user.IUserService;
import com.hkitemplate.demo.mvc.user.User;
import com.hkitemplate.demo.config.AccessLimit;


/**
 *
 * @author zhanghao
 * @since 2019-04-13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService iUserService;

    /**
     *
     * 添加User
     * @author zhanghao
     * @since 2019-04-13
     * @return
     */
    @RequestMapping(value = { "/user" }, method = RequestMethod.POST)
    public ResultBean<Boolean> addUser(User user) {
        return new ResultBean<Boolean>(iUserService.save(user));
    }

    /**
     *
     * 功能描述: 根据ID查找User
     *
     * @param: id
     * @return:
     * @author zhanghao
     * @since 2019-04-13
     */
    @AccessLimit(time=5,max=1)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResultBean<User> findUser(@PathVariable("id") int id) {
        return new ResultBean<User>(iUserService.getById(id));
    }

    /**
     *
     * 功能描述: 更新User
     *
     * @param: User
     * @return:
     * @author zhanghao
     * @since 2019-04-13
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResultBean<Boolean> updateUser(@RequestBody User user) {
        return new ResultBean<Boolean>(iUserService.updateById(user));
    }

    /**
     *
     * 功能描述: 删除User
     * @param:
     * @return:
     * @author zhanghao
     * @since 2019-04-13
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResultBean<Boolean> deleteUser(@ApiParam(required = true,name = "id",value = "id")
                                                @PathVariable("id") int id) {
        return new ResultBean<Boolean>(iUserService.removeById(id));
    }




    /**
     *
     * 功能描述: 分页查询所有
     *
     * @param: 总条数 getTotal 当前页数 getCurrent 每页显示数 getSize
     * @return:
     * @author zhanghao
     * @since 2019-04-13
     */
    @RequestMapping(value = { "/users", "/" }, method = RequestMethod.GET)
    public ResultBean<IPage<User>> findeAll(@ApiParam(required = true,name = "id",value = "当前用户id")
                                                    @RequestParam("id")Integer id,
                                                    @ApiParam(required = true,name = "current",value = "当前页")
                                                    @RequestParam("current")Integer current,
                                                    @ApiParam(required = true,name = "size",value = "每页条数")
                                                    @RequestParam("size")Integer size)  {
        Page<User> page = new Page<>(current, size);
        IPage<User> userIPage = iUserService.page(page, new QueryWrapper<User>()
        .lambda()
        .eq(User::getId, id));
        return new ResultBean<IPage<User>>(userIPage);
    }

}