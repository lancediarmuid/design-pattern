package singlepattern;

import singlepattern.commonobject.DistributedLock;
import singlepattern.commonobject.SharedObjectStorage;

/**
 * 集群间唯一单例模式
 *      集群环境下，我们需要依赖一个共享的外部存储区来充当 ThreadLocal 这样的角色。
 */
public class ClusterSingleton {

    private ClusterSingleton(){}

    private static ClusterSingleton instance;

    // 引入集群共享的外部中间件
    private static SharedObjectStorage sharedObjectStorage = new SharedObjectStorage("/Users/faker/Desktop/git projects/design-pattern/patterns/singlepattern/commonobject");

    // 引入分布式锁
    private static DistributedLock distributedLock = new DistributedLock();

    // 获取集群中唯一的单例实例
    public static synchronized ClusterSingleton getInstance() throws Exception {
        if (instance == null){
            distributedLock.lock();
            instance = (ClusterSingleton) sharedObjectStorage.load(ClusterSingleton.class);
        }
        return instance;
    }

    // 释放资源，并将对象更新保存回共享存储中间件
    public synchronized void freeInstance(){
        sharedObjectStorage.save(this, ClusterSingleton.class);
        instance = null;
        distributedLock.unlock();
    }

    public void func(){
        System.out.println("cluster foo");
    }

}
