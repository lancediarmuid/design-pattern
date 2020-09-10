package factorypattern;

import factorypattern.methodfactory.AttachmentMethodFactoryMap;
import factorypattern.methodfactory.IBusinessAttachmentFactory;
import factorypattern.simplefactory.SimpleAttachmentFactory;

/**
 * 工厂模式运行使用类
 */
public class FactoryRunner {

    public static final String EXCEPTION_STRING = "请求异常，未支持的业务类型";

    public static void main(String[] args) {
        // 简单工厂测试
        String businessType = "WorkOrder";
        IBusinessAttachment businessAttachment = SimpleAttachmentFactory.create(businessType);
        if (businessAttachment == null) {
            throw new RuntimeException(EXCEPTION_STRING);
        }
        System.out.println(businessAttachment.upload());

        // 工厂方法模式测试
        // 工厂方法模式，将复杂的业务对象创建过程解耦，一个工厂只负责一类对象的创建逻辑，符合单一职责原则
        //
        IBusinessAttachmentFactory factory = AttachmentMethodFactoryMap.getBusinessFactories(businessType);
        if (factory == null) {
            throw new RuntimeException(EXCEPTION_STRING);
        }
        IBusinessAttachment methodFactoryBusinessAttachment = factory.create();
        System.out.println(methodFactoryBusinessAttachment.upload());
    }
}
