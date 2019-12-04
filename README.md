# rxjava-exercise
rxjava1.x以及rxjava2.x两版本coding练习。

## rxjava调度器种类
- Schedulers.computation( ): 用于计算任务，如事件循环或和回调处理，不要用于IO操作(IO操作请使用Schedulers.io())；默认线程数等于处理器的数量
- Schedulers.from(executor): 使用指定的Executor作为调度器
- Schedulers.immediate( ): 在当前线程立即开始执行任务
- Schedulers.io( ): 用于IO密集型任务，如异步阻塞IO操作，这个调度器的线程池会根据需要增长；对于普通的计算任务，请使用Schedulers.computation()；Schedulers.io( )默认是一个CachedThreadScheduler，很像一个有线程缓存的新线程调度器
- Schedulers.newThread( ): 为每个任务创建一个新线程
- Schedulers.trampoline( ): 当其它排队的任务完成后，在当前线程排队开始执行

