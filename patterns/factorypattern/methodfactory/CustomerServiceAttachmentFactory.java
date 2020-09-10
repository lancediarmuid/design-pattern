package factorypattern.methodfactory;

import factorypattern.CustomerServiceOrderAttachment;
import factorypattern.IBusinessAttachment;

public class CustomerServiceAttachmentFactory implements IBusinessAttachmentFactory {
    @Override
    public IBusinessAttachment create() {
        return new CustomerServiceOrderAttachment();
    }
}
