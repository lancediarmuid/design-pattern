package singlepattern;

import singlepattern.commonobject.Config;

/**
 * 支持传参的单利模式
 */
public class ParamSingleton {
    private static ParamSingleton paramSingleton = null;

    private final String paramA;
    private final String paramB;

    private ParamSingleton(){
        this.paramA = Config.PARAM_A;
        this.paramB = Config.PARAM_B;
    }

    public synchronized static ParamSingleton getInstance(){
        if (paramSingleton == null){
            paramSingleton = new ParamSingleton();
        }
        return paramSingleton;
    }
}
