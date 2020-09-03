package singlepattern.commonobject;

/**
 * @author lance
 * @Description Config 是一个存储了 paramA 和 paramB 值的全局变量。
 *                  里面的值既通过静态常量来定义，也可以从配置文件中加载得到。
 * @createTime 2020-09-03 22:36
 */
public class Config {
    public static final String PARAM_A = "foo";
    public static final String PARAM_B = "bar";
}
