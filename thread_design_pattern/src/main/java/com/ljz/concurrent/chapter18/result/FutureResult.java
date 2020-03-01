package com.ljz.concurrent.chapter18.result;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 16:53
 * @version: V1.0
 */
public class FutureResult implements MyResult {
    private MyResult result;
    private boolean ready = false;

    public synchronized void  setResult(MyResult result) {
        this.result = result ;
        this.ready = true;
        this.notifyAll();
    }



    @Override
    public synchronized Object getResultValue() {
        while (!ready) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.result.getResultValue();
    }
}
