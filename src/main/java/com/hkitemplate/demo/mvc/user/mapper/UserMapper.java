package com.hkitemplate.demo.mvc.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hkitemplate.demo.mvc.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhanghao
 * @since 2019-04-13
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> findProfessor(Page page, List<Integer> levels, Integer memberCategory, Integer polling, Integer status, Integer tableId);

}
