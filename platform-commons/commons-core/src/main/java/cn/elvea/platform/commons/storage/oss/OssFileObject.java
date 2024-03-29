package cn.elvea.platform.commons.storage.oss;

import cn.elvea.platform.commons.enums.StorageTypeEnum;
import cn.elvea.platform.commons.storage.domain.AbstractFileObject;
import cn.elvea.platform.commons.storage.domain.FileObject;
import com.aliyun.oss.model.GenericResult;
import lombok.*;

import java.io.File;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OssFileObject extends AbstractFileObject<GenericResult> implements FileObject<GenericResult> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.OSS;

    private String key;

    private File object;

    private String url;

    private GenericResult response;

}
