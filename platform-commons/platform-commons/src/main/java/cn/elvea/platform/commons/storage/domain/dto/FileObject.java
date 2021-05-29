package cn.elvea.platform.commons.storage.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;

/**
 * 文件对象
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
@Setter
public class FileObject<R> implements Serializable {
    /**
     * 存储类型
     */
    protected String storageType;
    /**
     * 文件标识
     */
    protected String key;
    /**
     * 文件对象
     */
    protected File object;
    /**
     * R
     */
    protected R result;

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
