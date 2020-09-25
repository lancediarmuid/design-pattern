package observerpattern.simpleobserver;

import observerpattern.common.Message;

/**
 * 被观察者类接口
 * 提供接口：发送更新信息
 */
public interface Observer {
    void update(Message message);
}
