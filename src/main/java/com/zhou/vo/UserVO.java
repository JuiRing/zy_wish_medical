package com.zhou.vo;

import com.zhou.pojo.Menu;
import com.zhou.pojo.User;

import java.util.List;

public class UserVO {
    User user;
    List<Menu> menuList;
    @Override
    public String toString() {
        return "UserVO{" +
                "user=" + user +
                ", menuList=" + menuList+
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
