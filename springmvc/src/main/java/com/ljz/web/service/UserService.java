package com.ljz.web.service;

import com.ljz.web.entity.User;

import java.util.List;

/**
 * Copyright © 2020年03月02日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.demo.service
 * @Description:
 * @author: liujiaze
 * @date: 2020年03月02日 15:01
 * @version: V1.0
 */
public interface UserService {
    String get(String name);

    List<User> list();
}
