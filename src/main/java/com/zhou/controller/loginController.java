package com.zhou.controller;

import com.github.pagehelper.PageInfo;
import com.zhou.pojo.User;
import com.zhou.service.LoginService;
import com.zhou.vo.ResultVO;
import com.zhou.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("user")
@ResponseBody
public class loginController {
    @Resource
    private LoginService loginService;
    //登录验证
    @RequestMapping("login")
    public ResultVO<User> login(String username, String password, String inputVerify, String randomVerify, HttpServletRequest request){
        ResultVO<User> resultVO = new ResultVO<>();
        if (inputVerify.equals(randomVerify)){
            User user = loginService.login(username, password);
            if (user!=null){
                request.getSession().setAttribute("user",user.getU_loginName());
                return resultVO;
            }else {
                resultVO.setCode(300);
                resultVO.setMsg("用户名或者密码错误！！");
                return resultVO;
            }
        }else {
            resultVO.setCode(301);
            resultVO.setMsg("验证码错误！！");
            return resultVO;
        }
    }
    //退出登录
    @RequestMapping("logout")
    public int logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        request.getRequestDispatcher("/pages/login.html").forward(request,response);
        return 1;
    }
    //权限列表渲染
    @RequestMapping("loadRole")
    public UserVO loadRole(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("user");
        UserVO userVO = loginService.roleList(username);
        return userVO;
    }
}
