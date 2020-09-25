package observerpattern.simpleobserver.impl;

import observerpattern.common.Message;
import observerpattern.simpleobserver.Observer;

public class ConcreteObserverOne implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("ConcreteObserverOne is notified.");
    }
}
