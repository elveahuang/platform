package cn.elvea.platform.system.attachment.api;

import cn.elvea.platform.commons.core.storage.Storage;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.system.attachment.model.AttachmentFile;
import cn.elvea.platform.system.attachment.model.AttachmentParameter;
import cn.elvea.platform.system.attachment.service.AttachmentService;
import cn.elvea.platform.system.attachment.service.AttachmentTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class AttachmentApiImpl implements AttachmentApi {

    private final Storage storage;

    private final AttachmentService attachmentService;

    private final AttachmentTypeService attachmentTypeService;

    /**
     * @see AttachmentApi#uploadAttachmentFile(AttachmentParameter, MultipartFile)
     */
    @Override
    public AttachmentFile uploadAttachmentFile(AttachmentParameter parameter, MultipartFile file) {
        try {
            FileObject<?> object = this.storage.getStorageService().uploadFile(file);
            return AttachmentFile.builder().type(parameter.getType()).url(object.getUrl()).build();
        } catch (Exception e) {
            log.error("uploadAttachmentFile failed.", e);
        }
        return null;
    }

}
