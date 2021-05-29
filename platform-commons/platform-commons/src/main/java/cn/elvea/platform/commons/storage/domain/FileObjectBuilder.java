package cn.elvea.platform.commons.storage.domain;

import cn.elvea.platform.commons.storage.domain.dto.CosFileObject;
import cn.elvea.platform.commons.storage.domain.dto.MinFileObject;
import cn.elvea.platform.commons.storage.domain.dto.OssFileObject;

public abstract class FileObjectBuilder {

    public static MinFileObject.MinFileObjectBuilder minStorageBuilder() {
        return MinFileObject.builder();
    }

    public static OssFileObject.OssFileObjectBuilder ossStorageBuilder() {
        return OssFileObject.builder();
    }

    public static CosFileObject.CosFileObjectBuilder cosStorageBuilder() {
        return CosFileObject.builder();
    }

}
