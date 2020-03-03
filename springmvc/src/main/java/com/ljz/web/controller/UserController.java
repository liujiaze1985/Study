package com.ljz.web.controller;

import com.ljz.framework.annotation.FYAutowired;
import com.ljz.framework.annotation.FYController;
import com.ljz.framework.annotation.FYRequestMapping;
import com.ljz.framework.annotation.FYRequestParam;
import com.ljz.web.entity.User;
import com.ljz.web.service.UserService;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright © 2020年03月02日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.demo.controller
 * @Description:
 * @author: liujiaze
 * @date: 2020年03月02日 15:05
 * @version: V1.0
 */
@FYController
@FYRequestMapping("/user")
public class UserController {
    @FYAutowired
    private UserService userService;

    @FYRequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, @FYRequestParam("name") String name) throws IOException {
        String res = userService.get(name);
        System.out.println(name + "=>" + res);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(res);
        return "index";
    }

    @FYRequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userService.list();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(users.toString());
        return "list";
    }
}
