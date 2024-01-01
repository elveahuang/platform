package cn.elvea.platform.system.attachment.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.attachment.model.entity.AttachmentEntity;
import cn.elvea.platform.system.attachment.model.form.AttachmentForm;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface AttachmentService extends CachingEntityService<AttachmentEntity, Long> {

    /**
     * 上传附件
     */
    void saveAttachment(AttachmentForm form);

}
