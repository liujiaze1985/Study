# CyclicBarrier
可循环的Barrier（篱笆/栅栏） 功能类似于CountDownLatch
## API
* reset Cyclic名字的来源 ,将NumberWaiting重置为初始值
* await
* await
* breakBarrier
* dowait
* getNumberWaiting 获取还有几个在等待
* getParties 获取一共分了几部分
* isBroken 是否broken住 （reset时会broken住）
* nextGeneration
## CountDownLatch VS CyclicBarrier
1. CountDownLatch 不能reset， CyclicBarrier是可以循环使用的
2. CountDownLatch 工作线程之间互不关心， CyclicBarrier工作线程必须 等到同一个共同的点才去执行某个动作