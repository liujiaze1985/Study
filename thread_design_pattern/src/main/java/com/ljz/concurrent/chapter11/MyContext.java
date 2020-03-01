package com.ljz.concurrent.chapter11;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter11
 * @Description: 上下文对象
 * @author: liujiaze
 * @date: 2019年12月19日 14:54
 * @version: V1.0
 */
public class MyContext {

    private String name;
    private String cardId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
