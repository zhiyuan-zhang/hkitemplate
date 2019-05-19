package com.hkitemplate.demo.mvc.user.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hkitemplate.demo.mvc.user.User;
import com.hkitemplate.demo.mvc.user.mapper.UserMapper;
import com.hkitemplate.demo.mvc.user.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanghao
 * @since 2019-04-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Page<User> findProfessor(Page page, List<Integer> levels, Integer memberCategory, Integer polling, Integer status, Integer tableId) {
        return page.setRecords(this.baseMapper.findProfessor(page,levels,memberCategory,1,1,tableId) );
    }
}
