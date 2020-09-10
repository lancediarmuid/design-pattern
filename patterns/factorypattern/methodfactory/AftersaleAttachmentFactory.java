package factorypattern.methodfactory;

import factorypattern.AftersaleOrderAttachment;
import factorypattern.IBusinessAttachment;

public class AftersaleAttachmentFactory implements IBusinessAttachmentFactory {
    @Override
    public IBusinessAttachment create() {
        return new AftersaleOrderAttachment();
    }
}
