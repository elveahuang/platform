package cn.elvea.platform.commons.core.storage.min;

import cn.elvea.platform.commons.core.enums.StorageTypeEnum;
import cn.elvea.platform.commons.core.storage.domain.AbstractFileObject;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import io.minio.GetObjectResponse;
import lombok.*;

import java.io.File;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MinFileObject extends AbstractFileObject<GetObjectResponse> implements FileObject<GetObjectResponse> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.MIN;

    private String key;

    private String url;

    private File object;

    private GetObjectResponse response;

}
