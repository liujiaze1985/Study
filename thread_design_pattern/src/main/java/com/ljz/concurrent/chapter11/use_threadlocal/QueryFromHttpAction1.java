package com.ljz.concurrent.chapter11.use_threadlocal;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter11
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 14:59
 * @version: V1.0
 */
public class QueryFromHttpAction1 {

    public void execute() {
        //调用http里获取数据
        MyContext1 context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);//
        context.setCardId(cardId);  //放入上下文
    }


    /**
     * 调用http接口 根据名称获取身份证号
     * @param name
     * @return
     */
    private String getCardId(String name) {
        try {
            Thread.sleep(1000L);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "620423198601145458_" + Thread.currentThread().getId();
    }
}
