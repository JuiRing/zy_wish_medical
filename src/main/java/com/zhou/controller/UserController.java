package com.zhou.controller;

import com.github.pagehelper.PageInfo;
import com.zhou.pojo.User;
import com.zhou.service.UserService;
import com.zhou.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("users")
@ResponseBody
public class UserController {
    @Resource
    private UserService userService;
    //多条件分页查询
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultVO<User> queryByPage(Integer pageNum, Integer pageSize, String trueName) {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 5;
        }
        PageInfo<User> info = userService.queryByPage(pageNum, pageSize, trueName);
        //System.out.println(vo);
        return new ResultVO<>(info);
    }
}
