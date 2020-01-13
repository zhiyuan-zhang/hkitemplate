package com.hkitemplate.demo.mvc.user.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.exceptions.CheckException;
import com.hkitemplate.demo.mvc.user.User;
import com.hkitemplate.demo.mvc.user.mapper.UserMapper;
import com.hkitemplate.demo.mvc.user.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hkitemplate.demo.utils.FileUtil;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String regDepartementZip(List<String> stringList){
        String companyInfoExcelFilePath = "";
        String shareholderListExcelFilePath = "";
        List<String> fileList = Arrays.asList(companyInfoExcelFilePath,shareholderListExcelFilePath);
        if (ObjectUtils.isEmpty(fileList)) {
            throw new CheckException("当前表单下没有文件");
        }
        int id = RandomUtils.nextInt();
        String zipPath = id + "-资料压缩包.zip";
        String zipFtpUrl = null;
        FileUtil.ZipFiles(fileList, zipPath);
        return zipFtpUrl;
    }




}
