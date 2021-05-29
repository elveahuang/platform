package cn.elvea.platform.core.catalog.manager.impl;

import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.catalog.domain.entity.CatalogRelationEntity;
import cn.elvea.platform.core.catalog.manager.CatalogRelationManager;
import cn.elvea.platform.core.catalog.repository.CatalogRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static cn.elvea.platform.core.catalog.CatalogConstants.*;

/**
 * CatalogRelationManager
 *
 * @author elvea
 * @see CatalogRelationManager
 * @since 0.0.1
 */
@Service
@Slf4j
public class CatalogRelationManagerImpl extends AbstractEntityService<CatalogRelationEntity, Long, CatalogRelationRepository> implements CatalogRelationManager {

    /**
     * @see CatalogRelationManager#getParents(String, long)
     */
    @Override
    @Cacheable(value = CACHE_PARENT_CATALOG_RELATION, key = "#relationType + '-' + #entityId")
    public List<CatalogRelationEntity> getParents(String relationType, long entityId) {
        return this.repository.getParents(relationType, entityId);
    }

    /**
     * @see CatalogRelationManager#getDirectParents(String, long)
     */
    @Override
    @Cacheable(value = CACHE_DIRECT_PARENT_CATALOG_RELATION, key = "#relationType + '-' + #entityId")
    public List<CatalogRelationEntity> getDirectParents(String relationType, long entityId) {
        return this.repository.getDirectParents(relationType, entityId);
    }

    /**
     * @see CatalogRelationManager#getChildren(String, long)
     */
    @Override
    @Cacheable(value = CACHE_CHILD_CATALOG_RELATION, key = "#relationType + '-' + #ancestorId")
    public List<CatalogRelationEntity> getChildren(String relationType, long ancestorId) {
        return this.repository.getChildren(relationType, ancestorId);
    }

    /**
     * @see CatalogRelationManager#getDirectChildren(String, long)
     */
    @Override
    @Cacheable(value = CACHE_DIRECT_CHILD_CATALOG_RELATION, key = "#relationType + '-' + #ancestorId")
    public List<CatalogRelationEntity> getDirectChildren(String relationType, long ancestorId) {
        return this.repository.getDirectChildren(relationType, ancestorId);
    }

    /**
     * @see CatalogRelationManager#updateEntityRelations(String, Long, Long)
     */
    @Override
    @Caching(evict = {
            @CacheEvict(value = CACHE_PARENT_CATALOG_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = CACHE_DIRECT_PARENT_CATALOG_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = CACHE_CHILD_CATALOG_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = CACHE_DIRECT_CHILD_CATALOG_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true)
    })
    public void updateEntityRelations(String relationType, Long entityId, Long ancestorId) {

        List<CatalogRelationEntity> relationList = new ArrayList<>();

        // 根据关联类型，获取上级的关联类型，以便查询上级所有已有关联
        String ancestorRelationType = null;
        if (CATALOG_RELATION_CATALOG_PARENT_CATALOG.equalsIgnoreCase(relationType)) {
            ancestorRelationType = CATALOG_RELATION_CATALOG_PARENT_CATALOG;
        } else {
            //
        }

        List<CatalogRelationEntity> ancestorRelationList = this.getParents(ancestorRelationType, ancestorId);

        AtomicInteger index = new AtomicInteger(1);

        StringBuilder pathBuilder = new StringBuilder();

        // 先增加上级的上级关联
        if (CollectionUtils.isNotEmpty(ancestorRelationList)) {
            relationList = ancestorRelationList.stream().map(r -> {

                // 关联路径
                pathBuilder.append(CATALOG_RELATION_DELIMITER).append(r.getAncestorId());

                // 构建关联
                return CatalogRelationEntity.builder()
                        .ancestorId(r.getAncestorId())
                        .entityId(entityId)
                        .parentInd(Boolean.FALSE)
                        .relationType(relationType)
                        .relationIndex(index.getAndIncrement())
                        .build();
            }).collect(Collectors.toList());
        }

        // 增加上级关联
        relationList.add(CatalogRelationEntity.builder()
                .ancestorId(ancestorId)
                .entityId(entityId)
                .parentInd(Boolean.TRUE)
                .relationType(relationType)
                .relationIndex(index.getAndIncrement())
                .build()
        );
        pathBuilder.append(CATALOG_RELATION_DELIMITER).append(ancestorId).append(CATALOG_RELATION_DELIMITER);

        // 处理完整关联路径
        relationList.forEach(r -> {
            r.setFullPath(pathBuilder.toString());
        });

        // 删除已有的关联记录，然后保存新的关联记录

        this.repository.deleteAsChild(relationType, entityId);

        if (CollectionUtils.isNotEmpty(relationList)) {
            this.repository.saveAll(relationList);
        }

    }

    /**
     * @see CatalogRelationManager#deleteAsAncestor(String, Long)
     */
    @Override
    public void deleteAsAncestor(String relationType, Long ancestorId) {
        this.repository.deleteAsAncestor(relationType, ancestorId);
    }

    /**
     * @see CatalogRelationManager#deleteAsChild(String, Long)
     */
    @Override
    public void deleteAsChild(String relationType, Long entityId) {
        this.repository.deleteAsChild(relationType, entityId);
    }

    /**
     * @see CatalogRelationManager#clearCache()
     */
    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_PARENT_CATALOG_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = CACHE_DIRECT_PARENT_CATALOG_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = CACHE_CHILD_CATALOG_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = CACHE_DIRECT_CHILD_CATALOG_RELATION, beforeInvocation = true)
    })
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}]...", CACHE_PARENT_CATALOG_RELATION);
            log.debug("Clear cache [{}]...", CACHE_DIRECT_PARENT_CATALOG_RELATION);
            log.debug("Clear cache [{}]...", CACHE_CHILD_CATALOG_RELATION);
            log.debug("Clear cache [{}]...", CACHE_DIRECT_CHILD_CATALOG_RELATION);
        }
    }

}
