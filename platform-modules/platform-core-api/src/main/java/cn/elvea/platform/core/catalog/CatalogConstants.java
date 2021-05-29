package cn.elvea.platform.core.catalog;

/**
 * 目录分类相关全局变量
 *
 * @author elvea
 */
public interface CatalogConstants {

    // ========================================================================
    // 缓存
    // ========================================================================

    String CACHE_CATALOG_TYPE = "CACHE_CATALOG_TYPE";

    String CACHE_CATALOG = "CACHE_CATALOG";

    String CACHE_PARENT_CATALOG_RELATION = "CACHE_PARENT_CATALOG_RELATION";

    String CACHE_DIRECT_PARENT_CATALOG_RELATION = "CACHE_DIRECT_PARENT_CATALOG_RELATION";

    String CACHE_CHILD_CATALOG_RELATION = "CACHE_CHILD_CATALOG_RELATION";

    String CACHE_DIRECT_CHILD_CATALOG_RELATION = "CACHE_DIRECT_CHILD_CATALOG_RELATION";

    // ========================================================================
    // 目录关联
    // ========================================================================

    String CATALOG_RELATION_DELIMITER = "~|~";

    String CATALOG_RELATION_TYPE_PARENT = "_PARENT_";

    String CATALOG_RELATION_TYPE_CURRENT = "_CURRENT_";

    String CATALOG_RELATION_CATALOG_PARENT_CATALOG = "CATALOG_PARENT_CATALOG";


}
