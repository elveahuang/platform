package cn.elvea.platform.commons.core.storage;

import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.domain.FileParameter;
import cn.elvea.platform.commons.core.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 存储服务
 *
 * @author elvea
 * @since 0.0.1
 */
public interface StorageService {

    /**
     * 获取文件信息
     */
    default FileObject<?> getFile(String path) {
        return this.getFile(path, false);
    }

    /**
     * 获取文件信息
     */
    FileObject<?> getFile(String path, boolean withLocalTempFile);

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(MultipartFile file) throws Exception {
        FileParameter parameter = FileParameter.builder()
                .originalFilename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .size(file.getSize())
                .build();
        return this.uploadFile(file.getInputStream(), parameter);
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(File file) throws Exception {
        FileParameter parameter = FileParameter.builder()
                .originalFilename(file.getName())
                .contentType(FileUtils.getContentType(file))
                .size(FileUtils.getFileSize(file))
                .build();
        return this.uploadFile(file, parameter);
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(File file, FileParameter parameter) throws Exception {
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            return this.uploadFile(is, parameter);
        }
    }

    /**
     * 上传文件
     */
    FileObject<?> uploadFile(InputStream is, FileParameter parameter) throws Exception;

    /**
     * 创建临时文件夹
     *
     * @return File
     */
    File newTempFolder();

    /**
     * 在系统临时目录下创建一个临时文件
     *
     * @param filename 文件路径
     * @return 临时文件
     */
    File newTempFile(String filename) throws Exception;

    /**
     * 生成文件名
     *
     * @param ext 文件后缀名
     * @return String
     */
    String generateExtFilename(String ext);

}
