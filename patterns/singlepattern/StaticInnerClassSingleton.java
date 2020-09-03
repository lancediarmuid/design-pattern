package singlepattern;

/**
 * 静态内部类实现单例
 *
 * StaticInnerClassSingletonHolder 是一个静态内部类，当外部类 StaticInnerClassSingleton 被加载的时候，
 * 并不会创建 StaticInnerClassSingletonHolder 实例对象。只有当调用 getInstance() 方法时，
 * StaticInnerClassSingletonHolder 才会被加载，这个时候才会创建 instance。instance 的唯一
 * 性、创建过程的线程安全性，都由 JVM 来保证。所以，这种实现方法既保证了线程
 * 安全，又能做到延迟加载。
 */
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton(){}

    private static class StaticInnerClassSingletonHolder{
        private static final StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance(){
        return StaticInnerClassSingletonHolder.staticInnerClassSingleton;
    }

    public String func(){
        return "static inner class singleton";
    }

}
