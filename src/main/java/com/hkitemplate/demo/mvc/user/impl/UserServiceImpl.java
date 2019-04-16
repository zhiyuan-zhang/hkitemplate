package com.hkitemplate.demo.mvc.user.impl;

import com.hkitemplate.demo.mvc.user.User;
import com.hkitemplate.demo.mvc.user.mapper.UserMapper;
import com.hkitemplate.demo.mvc.user.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
