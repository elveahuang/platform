package cn.elvea.platform.persistence.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DbType
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
@AllArgsConstructor
public enum DbType {
    /**
     * MYSQL
     */
    MYSQL("mysql", "MySQL"),
    /**
     * MARIADB
     */
    MARIADB("mariadb", "MARIADB"),
    /**
     * PostgreSQL
     */
    POSTGRESQL("postgresql", "POSTGRESQL"),
    /**
     * Oracle
     */
    ORACLE("oracle", "Oracle"),
    /**
     * Unknown
     */
    UNKNOWN("unknown", "Unknown");

    /**
     * 数据库名称
     */
    private final String code;
    /**
     * 描述
     */
    private final String description;

    public static DbType getDbType(String dbType) {
        for (DbType type : DbType.values()) {
            if (type.code.equalsIgnoreCase(dbType)) {
                return type;
            }
        }
        return UNKNOWN;
    }

}
