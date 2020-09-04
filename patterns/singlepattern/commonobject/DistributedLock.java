package singlepattern.commonobject;

/**
 * 模拟分布式锁，分布式锁，应该拥有两个接口，lock() 和 unlock()
 *
 */
public class DistributedLock {

    public void lock(){
        System.out.println("分布式锁，上锁操作。");
    }

    public void unlock(){
        System.out.println("分布式锁，解锁操作");
    }
}
