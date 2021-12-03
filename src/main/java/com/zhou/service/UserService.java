package com.zhou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.mapper.UserMapper;
import com.zhou.pojo.User;
import com.zhou.pojo.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public PageInfo<User> queryByPage(Integer pageNum, Integer pageSize, String trueName){
        //多条件
        UserExample example = new UserExample();
        //创建盛放条件的容器
        UserExample.Criteria criteria = example.createCriteria();
        if (trueName != null && !"".equals(trueName.trim())) {
            criteria.andU_trueNameLike("%" + trueName + "%");
        }
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<User> teamList = userMapper.selectByExample(example);
        return new PageInfo<>(teamList);
    }
}
