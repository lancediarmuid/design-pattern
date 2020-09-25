package observerpattern.simpleobserver;

import observerpattern.common.Message;
import observerpattern.simpleobserver.impl.ConcreteObserverOne;
import observerpattern.simpleobserver.impl.ConcreteObserverTwo;
import observerpattern.simpleobserver.impl.ConcreteSubject;

public class SimpleRunner {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());

        subject.notifyObservers(new Message());
    }
}
