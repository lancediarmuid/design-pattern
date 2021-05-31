package observerpattern.simpleobserver;


import observerpattern.common.Message;


/**
 * 观察者类接口
 * 提供：注册、注销、发送订阅消息的接口
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Message message);
}
