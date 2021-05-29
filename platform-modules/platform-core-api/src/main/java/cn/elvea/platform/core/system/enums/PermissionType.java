package cn.elvea.platform.core.system.enums;

/**
 * 权限类型
 *
 * @author elvea
 * @since 0.0.1
 */
public enum PermissionType {
    SYSTEM(1, "系统管理权限"),
    ADMIN(2, "管理权限"),
    USER(3, "用户权限");

    // 类型
    private final int type;
    // 描述
    private final String desc;

    PermissionType(final int type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static PermissionType getPermissionType(int type) {
        PermissionType[] ts = PermissionType.values();
        for (PermissionType t : ts) {
            if (t.getType() == type) {
                return t;
            }
        }
        return USER;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
