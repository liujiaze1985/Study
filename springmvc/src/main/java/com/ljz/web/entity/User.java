package com.ljz.web.entity;

/**
 * Copyright © 2020年03月02日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.demo
 * @Description:
 * @author: liujiaze
 * @date: 2020年03月02日 14:59
 * @version: V1.0
 */
public class User {

    private String id;
    private String username;
    private String password;

    public User() {
    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ", \"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
    }
}
