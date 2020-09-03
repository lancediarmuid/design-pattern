package singlepattern;

/**
 * 懒汉式单例
 * 缺点：
 *  不支持高并发，获取对象的方法是串行执行的，并发度低
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    private LazySingleton(){}

    /**
     * getInstance() synchronized锁，如果频繁使用，会造成性能瓶颈
     * @return
     */
    public static synchronized LazySingleton getInstance(){
        if (lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    public String func(){
        return "lazy singleton";
    }

    public static void main(String[] args) {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        String result = lazySingleton.func();
        System.out.println(result);
    }

}
