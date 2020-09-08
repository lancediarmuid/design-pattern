package factorypattern.simplefactory;


import factorypattern.*;

/**
 * 简单工厂
 * 背景设定：
 *     附件的CURD，根据不同的业务类型分别创建不同的业务类型附件对象
 */
public class SimpleAttachmentFactory {
    public static IBusinessAttachment create(String businessType){
        IBusinessAttachment iBusinessAttachment = null;
        if ("WorkOrder".equals(businessType)){
            iBusinessAttachment = new WorkOrderAttachment();
        }else if ("AftersaleOrder".equals(businessType)){
            iBusinessAttachment = new AftersaleOrderAttachment();
        }else if ("CustomerServiceOrder".equals(businessType)){
            iBusinessAttachment = new CustomerServiceOrderAttachment();
        }else if ("RefundOrder".equals(businessType)){
            iBusinessAttachment = new RefundOrderAttachment();
        }else if ("BrandOrder".equals(businessType)){
            iBusinessAttachment = new BrandOrderAttachment();
        }

        return iBusinessAttachment;
    }
}
