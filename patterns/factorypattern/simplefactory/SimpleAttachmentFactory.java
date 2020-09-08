package factorypattern.simplefactory;


import factorypattern.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂
 * 背景设定：
 *     附件的CURD，根据不同的业务类型分别创建不同的业务类型附件对象
 */
public class SimpleAttachmentFactory {

    private static final Map<String, IBusinessAttachment> cachedMap = new HashMap<>();

    static {
        cachedMap.put("WorkOrder", new WorkOrderAttachment());
        cachedMap.put("AftersaleOrder", new AftersaleOrderAttachment());
        cachedMap.put("CustomerServiceOrder", new CustomerServiceOrderAttachment());
        cachedMap.put("RefundOrder", new RefundOrderAttachment());
        cachedMap.put("BrandOrder", new BrandOrderAttachment());
    }

    public static IBusinessAttachment create(String businessType){
        if (businessType == null|| businessType.isEmpty()){
            return null;
        }

        IBusinessAttachment iBusinessAttachment = cachedMap.get(businessType);

        return iBusinessAttachment;
    }
}
