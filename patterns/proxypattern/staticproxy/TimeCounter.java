package proxypattern.staticproxy;

/**
 * 时间计数器，模拟需要织入业务执行逻辑中的非业务代码
 */
public class TimeCounter {
    public void recordRequest(String methodName, Long duaringTime, Long startupTime) {
        System.out.println(String.format("调用方法名：%s，执行耗时：%s，开始时间：%s。", methodName, String.valueOf(duaringTime),
                String.valueOf(startupTime)));
    }
}