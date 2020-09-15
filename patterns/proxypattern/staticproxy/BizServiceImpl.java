package proxypattern.staticproxy;

public class BizServiceImpl implements IBizService {

    @Override
    public String query(String param) {
        int j = 2;
        for (int i = 0; i < 100; i++) {
            j *= j;
        }
        // 延时 3s 模式业务查询场景
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // 线程异常处理
            e.printStackTrace();
        }
        return "执行业务查询方法。。。" + param;
    }

}