package cn.elvea.platform.commons.storage.enums;

import lombok.Getter;

/**
 * FileAccessTypeEnum
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum FileAccessType {
    /**
     * 公开
     */
    PUBLIC("PUBLIC", "公开"),
    /**
     * 私有
     */
    PRIVATE("PRIVATE", "私有");

    /**
     * 类型
     */
    private final String type;
    /**
     * 描述
     */
    private final String desc;

    FileAccessType(final String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static FileAccessType getFileAccessType(String type) {
        FileAccessType[] ts = FileAccessType.values();
        for (FileAccessType t : ts) {
            if (t.getType().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return PUBLIC;
    }

}
