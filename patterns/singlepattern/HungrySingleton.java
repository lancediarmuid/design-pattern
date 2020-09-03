package singlepattern;

/**
 * 饿汉式单例
 * 缺点：
 *  不支持延迟加载，当单例类不是高频使用时，是对系统资源的一种占用
 */
public class HungrySingleton{

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }

    public String func(){
        return "hungry singleton";
    }

    public static void main(String[] args) {
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        String result = hungrySingleton.func();
        System.out.println(result);
    }
}