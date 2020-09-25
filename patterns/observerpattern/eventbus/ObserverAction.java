package observerpattern.eventbus;

import java.lang.reflect.Method;

public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method){
        if (target != null){
            this.target = target;
        }
        this.method = method;
        this.method.setAccessible(true);
    }

    public void execute(Object event){
        try {
            method.invoke(target, event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
