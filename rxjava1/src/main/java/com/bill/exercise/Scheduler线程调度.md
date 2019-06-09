## Scheduler的类型
- 多次调用observeOn会影响从其调用位置开始的后面所有操作符和Subscriber
- 多次调用subscribeOn时，只有最上面的那个起作用，所以subscribeOn只调用一次就行了

### Scheduler分类
- computation: computation Scheduler适用于和CPU有关的任务，但不适合那些会造成阻塞的任务，如访问磁盘和网络等。computation Scheduler内部
    会根据当前运行环境的CPU核心数来创建一个线程池，里面的每个线程会占用一个CPU的核心
    
- newThread: newThread每次都会新建一个线程，适用于那些工作时间长并且总数少的任务
    
- io: io Scheduler类似于newThread Scheduler，不同之处在于其线程可以被回收利用。io所使用的线程池是不限大小的，如果有足够多的任务同时使用io就会导致内存不足

- immediate: immediate Scheduler会在当前的现呈上立即开始执行任务，这会将当前线程上正在进行的任务阻塞。
```
Outer start
Inner start
Inner end
Outer end
```

- trampoline: trampoline并不是立即开始执行任务的，而是等待当前线程上之前的任务都结束之后再开始执行
```
Outer start
Outer end
Inner start
Inner end
```

- from: 使用Schedulers.from(Executor executor)工厂方法来根据我们提供的Executor创建Scheduler