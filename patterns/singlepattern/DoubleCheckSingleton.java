package singlepattern;

/**
 * 双重校验单例实现
 * 支持高并发，并且只吃延迟加载
 */
public class DoubleCheckSingleton {

    private DoubleCheckSingleton(){}

    private static volatile DoubleCheckSingleton doubleCheckSingleton;


    public static DoubleCheckSingleton getInstance(){
        if (doubleCheckSingleton == null){
            synchronized (DoubleCheckSingleton.class){
                if (doubleCheckSingleton == null){
                    doubleCheckSingleton = new DoubleCheckSingleton();
                }
            }
        }
        return doubleCheckSingleton;
    }

    public String func( ){
        return "double check singleton";
    }
}
