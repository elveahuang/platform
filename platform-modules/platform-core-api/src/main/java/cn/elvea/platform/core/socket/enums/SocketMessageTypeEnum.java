package cn.elvea.platform.core.socket.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * 消息类型枚举定义
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum SocketMessageTypeEnum implements Serializable {
    /**
     * 未定义
     */
    UNSPECIFIED("UNSPECIFIED", "未定义"),
    /**
     * 本地存储
     */
    TEST("PREVENT_HANG_UP", "测试消息");

    private final String type;
    private final String description;

    SocketMessageTypeEnum(final String type, final String description) {
        this.type = type;
        this.description = description;
    }

    public static SocketMessageTypeEnum getSocketMessageType(String type) {
        SocketMessageTypeEnum[] ts = SocketMessageTypeEnum.values();
        for (SocketMessageTypeEnum t : ts) {
            if (t.getType().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return UNSPECIFIED;
    }

}
