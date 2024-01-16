package cn.elvea.platform.system.attachment.api;

import cn.elvea.platform.system.attachment.model.AttachmentFile;
import cn.elvea.platform.system.attachment.model.AttachmentParameter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface AttachmentApi {

    AttachmentFile uploadAttachmentFile(AttachmentParameter parameter, MultipartFile file);

}
