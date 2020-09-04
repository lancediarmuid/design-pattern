package singlepattern.commonobject;

import singlepattern.ClusterSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 模拟集群中的共享存储设施，在正常的生产环境中，这个中间件可能会是 mongoDB、Redis甚至还能是Memcached
 * 使用这些外部存储中间件时，需要做好对象的序列化和反序列化，本模拟外部对象没有实现此部分代码，此例为了模拟集群中的单例模式
 * 模拟使用过程中，需要实现两个接口分别是 load() 和 save()，
 * 将对象从存储中间件中加载进来，以及操作完成后，将对象更新保存会中间件中
 */
public class SharedObjectStorage<T> {

    // 存储文件路径
    private String filePath;

    public SharedObjectStorage(String filePath){
        this.filePath = filePath;
    }

    /**
     * 加载对象
     * @param clusterSingletonClass
     */
    public T load(Class<T> clusterSingletonClass) throws Exception {
        System.out.println("从中间件加载对象信息");
        // 模拟操作，实际代码编写不这样写
        Constructor c = clusterSingletonClass.getDeclaredConstructor();
        c.setAccessible(true);
        return (T) c.newInstance();
    }

    /**
     * 保存更新对象
     * @param clusterSingleton
     * @param clusterSingletonClass
     */
    public void save(T clusterSingleton, Class<T> clusterSingletonClass){
        System.out.println("将对象信息更新回存储中间件中");
    }
}
