package observerpattern.simpleobserver.impl;

import observerpattern.common.Message;
import observerpattern.simpleobserver.Observer;

public class ConcreteObserverTwo implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("ConcreteObserverTwo is notifed.");
    }
}
