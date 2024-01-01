package cn.elvea.platform.system.attachment.api;

import cn.elvea.platform.system.attachment.model.AttachmentFile;
import cn.elvea.platform.system.attachment.model.AttachmentParameter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface AttachmentApi {

    AttachmentFile uploadAttachmentFile(AttachmentParameter parameter, MultipartFile file);

}
