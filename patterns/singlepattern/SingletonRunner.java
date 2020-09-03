package singlepattern;

public class SingletonRunner {

    public static void main(String[] args) {
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
        System.out.println("静态内部类输出："+result);
        System.out.println();
    }
}
