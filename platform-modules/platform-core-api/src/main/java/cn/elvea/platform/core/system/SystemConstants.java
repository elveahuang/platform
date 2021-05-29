package cn.elvea.platform.core.system;

/**
 * SystemConstants
 *
 * @author elvea
 * @since 0.0.1
 */
public interface SystemConstants {

    // ========================================================================
    // 缓存
    // ========================================================================

    String CACHE_CLIENT = "CACHE_CLIENT";

    String CACHE_ROLE_TYPE = "CACHE_ROLE_TYPE";

    String CACHE_ROLE = "CACHE_ROLE";

    String CACHE_USER = "CACHE_USER";

    String CACHE_USER_SESSION = "CACHE_USER_SESSION";

    String CACHE_POSITION = "CACHE_POSITION";

    String CACHE_AUTHORITY = "CACHE_PERMISSION";

    String CACHE_USER_ROLE_RELATION = "CACHE_USER_ROLE_RELATION";

    String CACHE_ROLE_PERMISSION_RELATION = "CACHE_ROLE_PERMISSION_RELATION";

    String CACHE_PARENT_ENTITY_RELATION = "CACHE_PARENT_ENTITY_RELATION";

    String CACHE_DIRECT_PARENT_ENTITY_RELATION = "CACHE_DIRECT_PARENT_ENTITY_RELATION";

    String CACHE_CHILD_ENTITY_RELATION = "CACHE_CHILD_ENTITY_RELATION";

    String CACHE_DIRECT_CHILD_ENTITY_RELATION = "CACHE_DIRECT_CHILD_ENTITY_RELATION";

    // ========================================================================
    // 实体关联
    // ========================================================================

    String ENTITY_RELATION_DELIMITER = "~|~";

    String ENTITY_RELATION_TYPE_PARENT = "_PARENT_";

    String ENTITY_RELATION_TYPE_CURRENT = "_CURRENT_";

    String ENTITY_RELATION_DPT_PARENT_DPT = "DPT_PARENT_DPT";

    String ENTITY_RELATION_PST_PARENT_PST = "PST_PARENT_PST";

    String ENTITY_RELATION_USR_CURRENT_DPT = "USR_CURRENT_DPT";

    String ENTITY_RELATION_USR_CURRENT_PST = "USR_CURRENT_PST";

}
