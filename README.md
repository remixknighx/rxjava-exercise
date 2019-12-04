# rxjava-exercise
rxjava1.x以及rxjava2.x两版本coding练习。

## rxjava调度器种类
- Schedulers.computation( ): 用于计算任务，如事件循环或和回调处理，不要用于IO操作(IO操作请使用Schedulers.io())；默认线程数等于处理器的数量
- Schedulers.from(executor): 使用指定的Executor作为调度器
- Schedulers.immediate( ): 在当前线程立即开始执行任务
- Schedulers.io( ): 用于IO密集型任务，如异步阻塞IO操作，这个调度器的线程池会根据需要增长；对于普通的计算任务，请使用Schedulers.computation()；Schedulers.io( )默认是一个CachedThreadScheduler，很像一个有线程缓存的新线程调度器
- Schedulers.newThread( ): 为每个任务创建一个新线程
- Schedulers.trampoline( ): 当其它排队的任务完成后，在当前线程排队开始执行

## rxjava2调度器种类
- single：使用定长为1的线程池，重复利用这个线程
- newThread: 每次都启用新线程，并在新线程中执行操作
- computation：使用的固定的线程池，大小为CPU核数，适用于CPU密集型计算
- io：行为模式和newThread差不多，区别在于io()内部实现是用一个无数量上限的线程池，可以重用空闲的线程，因此IO()比newThread()更有效率
- trampoline: 直接在当前线程运行，如果当前线程有其他任务正在执行，则会先暂停其他任务
- Schedulers.from: 将java.util.concurrent.Executor转换成一个调度器实力，即可以自定义一个Executor来作为调度器
