package factorypattern.methodfactory;

import factorypattern.IBusinessAttachment;
import factorypattern.WorkOrderAttachment;

public class WorkOrderAttachmentFactory implements IBusinessAttachmentFactory {
    @Override
    public IBusinessAttachment create() {
        return new WorkOrderAttachment();
    }
}
