package cn.elvea.platform.commons.storage.cos;

import cn.elvea.platform.commons.enums.StorageTypeEnum;
import cn.elvea.platform.commons.storage.domain.AbstractFileObject;
import cn.elvea.platform.commons.storage.domain.FileObject;
import com.qcloud.cos.model.COSObject;
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
public class CosFileObject extends AbstractFileObject<COSObject> implements FileObject<COSObject> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.COS;

    private String key;

    private String url;

    private File object;

    private COSObject response;

}
