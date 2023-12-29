package cn.elvea.platform.commons.core.storage;

import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import cn.elvea.platform.commons.core.storage.domain.FileParameter;
import cn.elvea.platform.commons.core.utils.DateTimeUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class AbstractStorageService implements StorageService {

    protected String generateFilename(String filename) {
        return StringUtils.simpleUuid() + "." + FilenameUtils.getExtension(filename);
    }

    protected String generateFilename(FileParameter parameter) {
        String filename = parameter.getTargetFilename();
        if (StringUtils.isEmpty(filename)) {
            filename = generateFilename(parameter.getOriginalFilename());
        }
        return filename;
    }

    protected String generatePath(FileParameter parameter) {
        String path = parameter.getPath();
        if (StringUtils.isEmpty(path)) {
            path = DateTimeUtils.format(LocalDateTime.now(), DateTimeConstants.Pattern.SIMPLE_DATE);
        }
        return path;
    }

    protected String generateKey(FileParameter parameter, String name, String path) {
        return path + "/" + name;
    }

    protected String generateFileKey(FileParameter parameter) {
        String suffix = FilenameUtils.getExtension(parameter.getOriginalFilename());
        String uuid = IdUtil.simpleUUID();
        String path = DateTimeUtils.format(LocalDateTime.now(), DateTimeConstants.Pattern.SIMPLE_DATE);
        return path + "/" + uuid + "." + suffix;
    }

    /**
     * @see StorageService#generateExtFilename(String)
     */
    @Override
    public String generateExtFilename(String ext) {
        return StringUtils.uuid() + "." + ext;
    }

    /**
     * @see StorageService#newTempFolder()
     */
    @Override
    public File newTempFolder() {
        File tmpFile = new File(FileUtils.getTempDirectoryPath(), StringUtils.uuid());
        if (tmpFile.exists()) {
            try {
                FileUtils.forceDelete(tmpFile);
            } catch (IOException e) {
                log.error("file already exists, but delete failed!", e);
            }
        }
        if (tmpFile.mkdirs()) {
            return tmpFile;
        }
        return null;
    }

    @Override
    public File newTempFile(String filename) throws Exception {
        File tmpFile = new File(FileUtils.getTempDirectoryPath(), filename);
        // 强制建立目录，避免目录不存在报错
        FileUtils.forceMkdirParent(tmpFile);
        // 临时文件如果已经存在，强制删除，重新创建文件
        if (tmpFile.exists()) {
            try {
                FileUtils.forceDelete(tmpFile);
            } catch (IOException e) {
                log.error("Fail to create temp file [{}].", filename, e);
            }
        }
        if (tmpFile.createNewFile()) {
            log.info("create new file {}.", tmpFile.getAbsolutePath());
        }
        return tmpFile;
    }

}
