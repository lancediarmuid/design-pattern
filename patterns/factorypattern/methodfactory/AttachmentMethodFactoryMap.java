package factorypattern.methodfactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法模式
 * 工厂的工厂，以解决调用类的if-else问题
 */
public class AttachmentMethodFactoryMap {
    private static final Map<String, IBusinessAttachmentFactory> cachedFactoriesMap = new HashMap<>();

    // 缓存业务对象的工厂对象实例
    static {
        cachedFactoriesMap.put("WorkOrder", new WorkOrderAttachmentFactory());
        cachedFactoriesMap.put("AftersaleOrder", new AftersaleAttachmentFactory());
        cachedFactoriesMap.put("CustomerServiceOrder", new CustomerServiceAttachmentFactory());
        cachedFactoriesMap.put("RefundOrder", new RefundOrderAttachmentFactory());
        cachedFactoriesMap.put("BrandOrder", new BrandOrderAttachmentFactory());
    }

    /**
     * 获取业务对象工厂
     *
     * @param businessType  业务类型
     * @return  业务工厂
     */
    public static IBusinessAttachmentFactory getBusinessFactories(String businessType) {
        if (businessType == null || businessType.isEmpty()) {
            return null;
        }
        return cachedFactoriesMap.get(businessType);
    }
}
