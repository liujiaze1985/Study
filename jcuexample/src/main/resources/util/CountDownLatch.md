## 场景
    例1： 一批数据s， 处理（网络服务、加工），整体是串行化处理，如果处理每个数据的耗时比较慢，整体上会占用很多时间

    	解决方案： 多线程，将数据分为n批， 线程数为s/n
		com.ljz.concurrent.util.chapter4.CountDownLatchExample1
		并行处理数据，所有处理完之后，再串行化处理结果

	例2：
		com.ljz.concurrent.util.chapter4.CountDownLatchExample2
		两个线程做事，T1 执行一部分，T2执行另一部分，T1等T2执行完后再继续执行

	例3：
		不使用await


## API
	getCount()	：
    await()		:退出条件：被中断，或count为0
    await(long timeout, TimeUnit unit)	：
    countDown()	：
    