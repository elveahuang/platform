package cn.elvea.platform.commons.storage.domain.dto;

import com.qcloud.cos.model.COSObject;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 文件对象
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class OssFileObject extends FileObject<COSObject> implements Serializable {
}
