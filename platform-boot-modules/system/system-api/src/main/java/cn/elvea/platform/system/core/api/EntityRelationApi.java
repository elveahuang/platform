package cn.elvea.platform.system.core.api;

import cn.elvea.platform.system.catalog.model.dto.CatalogRelationSaveDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface EntityRelationApi {

    void saveEntityRelation(CatalogRelationSaveDto saveDto);

}
