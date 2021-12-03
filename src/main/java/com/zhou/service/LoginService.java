package com.zhou.service;

import com.zhou.mapper.MenuMapper;
import com.zhou.mapper.RoleMenuMapper;
import com.zhou.mapper.UserMapper;
import com.zhou.pojo.*;
import com.zhou.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private MenuMapper menuMapper;
    //登录用户验证
    public User login(String username, String password){
        //查询对应用户名的用户
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andU_loginNameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        User user = null;
        for (int i = 0; i < users.size(); i++) {
            user = users.get(i);
        }
        if (user!=null&&password.equals(user.getU_passWord())){
            return user;
        }else return null;
    }
    //获取用户对应的权限列表
    public UserVO roleList(String username){
        //查询对应用户名的用户
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andU_loginNameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        User user = null;
        UserVO userVO = new UserVO();
        List<Menu> menuList = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            user = users.get(i);
        }
        if (user!=null){
            //查询此用户对应的角色管理权限
            RoleMenuExample example1 =new RoleMenuExample();
            RoleMenuExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andR_idEqualTo(user.getR_id());
            List<RoleMenu> roleMenus = roleMenuMapper.selectByExample(example1);
            for (RoleMenu roleMenu : roleMenus) {
                //获得此用户角色对应的url
                Menu menu = menuMapper.selectByPrimaryKey(roleMenu.getM_id());
                menuList.add(menu);
            }
            userVO.setUser(user);
            userVO.setMenuList(menuList);
            return userVO;
        }else return null;
    }
}
