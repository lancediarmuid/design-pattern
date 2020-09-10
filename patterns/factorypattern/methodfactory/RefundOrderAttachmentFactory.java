package factorypattern.methodfactory;

import factorypattern.IBusinessAttachment;
import factorypattern.RefundOrderAttachment;

public class RefundOrderAttachmentFactory implements IBusinessAttachmentFactory {
    @Override
    public IBusinessAttachment create() {
        return new RefundOrderAttachment();
    }
}
