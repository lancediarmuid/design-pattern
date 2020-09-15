package proxypattern.staticproxy;

public class BizServiceProxy implements IBizService {

    private TimeCounter timecounter;
    private BizServiceImpl bizServiceImpl;

    public BizServiceProxy(BizServiceImpl bizServiceImpl) {
        this.timecounter = new TimeCounter();
        this.bizServiceImpl = bizServiceImpl;
    }

    @Override
    public String query(String param) {
        // TODO Auto-generated method stub
        Long startupTime = System.currentTimeMillis();
        // 委托
        String result = bizServiceImpl.query(param);
        Long duaringTime = System.currentTimeMillis() - startupTime;
        timecounter.recordRequest("query", duaringTime, startupTime);
        return result;
    }

}