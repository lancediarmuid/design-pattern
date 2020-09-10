package factorypattern.methodfactory;

import factorypattern.IBusinessAttachment;

public interface IBusinessAttachmentFactory {
    IBusinessAttachment create();
}
