package com.hkitemplate.demo.mvc.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hkitemplate.demo.mvc.user.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanghao
 * @since 2019-04-13
 */
public interface IUserService extends IService<User> {
    public Page<User> findProfessor(Page page, List<Integer> levels, Integer memberCategory, Integer polling, Integer status, Integer tableId);
}
