package factorypattern;

/**
 * 工单附件
 */
public class WorkOrderAttachment implements IBusinessAttachment {
    @Override
    public String upload() {
        return "工单附件上传操作...";
    }
}
