package singlepattern;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程间唯一单例模式
 *      通过一个 HashMap 来存储对象，其中 key 是线程 ID，value 是对象。这样就可以做到，不同线程对应不同的对象。
 *      Java 原生的实现可以使用 ThreadLocal 工具，底层也一样是使用到了 HashMap。
 */
public class ThreadSingleton {

    private Long singleThreadId;

    private final static ConcurrentHashMap<Long, ThreadSingleton> instances = new ConcurrentHashMap<>();

    private ThreadSingleton(){}

    public static ThreadSingleton getInstance(){
        Long threadId = Thread.currentThread().getId();
        instances.putIfAbsent(threadId, new ThreadSingleton());
        return instances.get(threadId);
    }
}
