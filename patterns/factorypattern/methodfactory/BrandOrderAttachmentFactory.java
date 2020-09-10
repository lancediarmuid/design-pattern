package factorypattern.methodfactory;

import factorypattern.BrandOrderAttachment;
import factorypattern.IBusinessAttachment;

public class BrandOrderAttachmentFactory implements IBusinessAttachmentFactory {
    @Override
    public IBusinessAttachment create() {
        return new BrandOrderAttachment();
    }
}
