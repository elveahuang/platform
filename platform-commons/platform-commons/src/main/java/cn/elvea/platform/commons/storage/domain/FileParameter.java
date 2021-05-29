package cn.elvea.platform.commons.storage.domain;

import cn.elvea.platform.commons.storage.enums.FileAccessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件参数
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileParameter implements Serializable {

    /**
     * 文件访问类型
     */
    private FileAccessType accessType;

    public static FileParameter withPublic() {
        return FileParameter.builder().accessType(FileAccessType.PUBLIC).build();
    }

    public static FileParameter withPrivate() {
        return FileParameter.builder().accessType(FileAccessType.PRIVATE).build();
    }

    public static FileParameter withDefault() {
        return FileParameter.withPublic();
    }

}
