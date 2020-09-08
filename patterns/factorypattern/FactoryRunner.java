package factorypattern;

import factorypattern.simplefactory.SimpleAttachmentFactory;

/**
 * 工厂模式运行使用类
 */
public class FactoryRunner {

    public static void main(String[] args) {
        // 简单工厂测试
        String businessType = "WorkOrder";
        IBusinessAttachment businessAttachment = SimpleAttachmentFactory.create(businessType);
        if (businessAttachment == null){
            throw new RuntimeException("请求异常，为支持的业务类型");
        }
        System.out.println(businessAttachment.upload());
    }
}
