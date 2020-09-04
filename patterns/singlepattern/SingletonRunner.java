package singlepattern;

public class SingletonRunner {

    public static void main(String[] args) throws Exception {
        String result = null;

        // 饿汉式
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        result = hungrySingleton.func();
        System.out.println("恶汉式输出：" + result);
        System.out.println();

        // 懒汉式
        LazySingleton lazySingleton = LazySingleton.getInstance();
        result = lazySingleton.func();
        System.out.println("懒汉式输出：" + result);
        System.out.println();

        // 双重校验
        DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
        result = doubleCheckSingleton.func();
        System.out.println("双重校验输出：" + result);
        System.out.println();

        // 静态内部类
        StaticInnerClassSingleton staticInnerClassSingleton = StaticInnerClassSingleton.getInstance();
        result = staticInnerClassSingleton.func();
        System.out.println("静态内部类输出：" + result);
        System.out.println();

        // 带参数的单例
        ParamSingleton paramSingleton = ParamSingleton.getInstance();
        result = paramSingleton.func();
        System.out.println("带参数的单例输出：" + result);
        System.out.println();

        // 线程间唯一单例模式
        for (int i = 0; i < 10; i++) {
            // 启动10个子线程
            new Thread(() -> {
                ThreadSingleton threadSingleton1 = ThreadSingleton.getInstance();
                ThreadSingleton threadSingleton2 = ThreadSingleton.getInstance();
                // 打印查看是否是相同的实例
                System.out.println(threadSingleton1);
                System.out.println(threadSingleton2);
            }).start();
        }

        // 集群中唯一单例模式，使用方式
        ClusterSingleton clusterSingleton = ClusterSingleton.getInstance();
        clusterSingleton.func();
        clusterSingleton.freeInstance();
    }
}
