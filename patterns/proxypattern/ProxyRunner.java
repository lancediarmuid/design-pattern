package proxypattern;

import proxypattern.dynamicproxy.TimeCounterProxy;
import proxypattern.staticproxy.BizServiceImpl;
import proxypattern.staticproxy.BizServiceProxy;
import proxypattern.staticproxy.IBizService;

public class ProxyRunner {
    public static void main(String[] args) {
        // 静态代理使用测试
        IBizService iBizService = new BizServiceProxy(new BizServiceImpl());
        String result = iBizService.query("foo");
        System.out.println("处理结果：" + result);

        // 动态代理使用测试
        TimeCounterProxy proxy = new TimeCounterProxy();
        IBizService iBizService2 = (IBizService) proxy.createProxy(new BizServiceImpl());
        String result2 = iBizService2.query("dynamic");

        System.out.println("处理结果：" + result2);
    }
}