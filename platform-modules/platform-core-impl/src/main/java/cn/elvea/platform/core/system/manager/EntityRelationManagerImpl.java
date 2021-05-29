package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.SystemConstants;
import cn.elvea.platform.core.system.domain.entity.EntityRelationEntity;
import cn.elvea.platform.core.system.manager.EntityRelationManager;
import cn.elvea.platform.core.system.repository.EntityRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * EntityRelationManager
 *
 * @author elvea
 * @see EntityRelationManager
 * @since 0.0.1
 */
@Slf4j
@Service
public class EntityRelationManagerImpl extends AbstractEntityService<EntityRelationEntity, Long, EntityRelationRepository> implements EntityRelationManager {

    /**
     * @see EntityRelationManager#getParents(String, long)
     */
    @Override
    @Cacheable(value = SystemConstants.CACHE_PARENT_ENTITY_RELATION, key = "#relationType + '-' + #entityId")
    public List<EntityRelationEntity> getParents(String relationType, long entityId) {
        return this.repository.getParents(relationType, entityId);
    }

    /**
     * @see EntityRelationManager#getDirectParents(String, long)
     */
    @Override
    @Cacheable(value = SystemConstants.CACHE_DIRECT_PARENT_ENTITY_RELATION, key = "#relationType + '-' + #entityId")
    public List<EntityRelationEntity> getDirectParents(String relationType, long entityId) {
        return this.repository.getDirectParents(relationType, entityId);
    }

    /**
     * @see EntityRelationManager#getChildren(String, long)
     */
    @Override
    @Cacheable(value = SystemConstants.CACHE_CHILD_ENTITY_RELATION, key = "#relationType + '-' + #ancestorId")
    public List<EntityRelationEntity> getChildren(String relationType, long ancestorId) {
        return this.repository.getChildren(relationType, ancestorId);
    }

    /**
     * @see EntityRelationManager#getDirectChildren(String, long)
     */
    @Override
    @Cacheable(value = SystemConstants.CACHE_DIRECT_CHILD_ENTITY_RELATION, key = "#relationType + '-' + #ancestorId")
    public List<EntityRelationEntity> getDirectChildren(String relationType, long ancestorId) {
        return this.repository.getDirectChildren(relationType, ancestorId);
    }

    /**
     * @see EntityRelationManager#updateEntityRelations(String, Long, Long)
     */
    @Override
    @Caching(evict = {
            @CacheEvict(value = SystemConstants.CACHE_PARENT_ENTITY_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = SystemConstants.CACHE_DIRECT_PARENT_ENTITY_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = SystemConstants.CACHE_CHILD_ENTITY_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true),
            @CacheEvict(value = SystemConstants.CACHE_DIRECT_CHILD_ENTITY_RELATION,
                    key = "#relationType + '-' + #entityId", beforeInvocation = true)
    })
    public void updateEntityRelations(String relationType, Long entityId, Long ancestorId) {

        List<EntityRelationEntity> relationList = new ArrayList<>();

        // 根据关联类型，获取上级的关联类型，以便查询上级所有已有关联
        String ancestorRelationType = null;
        if (SystemConstants.ENTITY_RELATION_DPT_PARENT_DPT.equalsIgnoreCase(relationType)) {
            ancestorRelationType = SystemConstants.ENTITY_RELATION_DPT_PARENT_DPT;
        } else if (SystemConstants.ENTITY_RELATION_PST_PARENT_PST.equalsIgnoreCase(relationType)) {
            ancestorRelationType = SystemConstants.ENTITY_RELATION_PST_PARENT_PST;
        } else if (SystemConstants.ENTITY_RELATION_USR_CURRENT_DPT.equalsIgnoreCase(relationType)) {
            ancestorRelationType = SystemConstants.ENTITY_RELATION_PST_PARENT_PST;
        } else if (SystemConstants.ENTITY_RELATION_USR_CURRENT_PST.equalsIgnoreCase(relationType)) {
            ancestorRelationType = SystemConstants.ENTITY_RELATION_PST_PARENT_PST;
        }

        List<EntityRelationEntity> ancestorRelationList = this.getParents(ancestorRelationType, ancestorId);

        AtomicInteger index = new AtomicInteger(1);

        StringBuilder pathBuilder = new StringBuilder();

        // 先增加上级的上级关联
        if (CollectionUtils.isNotEmpty(ancestorRelationList)) {
            relationList = ancestorRelationList.stream().map(r -> {

                // 关联路径
                pathBuilder.append(SystemConstants.ENTITY_RELATION_DELIMITER).append(r.getAncestorId());

                // 构建关联
                return EntityRelationEntity.builder()
                        .ancestorId(r.getAncestorId())
                        .entityId(entityId)
                        .parentInd(Boolean.FALSE)
                        .relationType(relationType)
                        .index_(index.getAndIncrement())
                        .build();
            }).collect(Collectors.toList());
        }

        // 增加上级关联
        relationList.add(EntityRelationEntity.builder()
                .ancestorId(ancestorId)
                .entityId(entityId)
                .parentInd(Boolean.TRUE)
                .relationType(relationType)
                .index_(index.getAndIncrement())
                .build()
        );
        pathBuilder.append(SystemConstants.ENTITY_RELATION_DELIMITER).append(ancestorId).append(SystemConstants.ENTITY_RELATION_DELIMITER);

        // 处理完整关联路径
        relationList.forEach(r -> {
            r.setPath_(pathBuilder.toString());
        });

        // 删除已有的关联记录，然后保存新的关联记录

        this.repository.deleteAsChild(relationType, entityId);

        if (CollectionUtils.isNotEmpty(relationList)) {
            this.repository.saveAll(relationList);
        }

    }

    /**
     * @see EntityRelationManager#deleteAsAncestor(String, Long)
     */
    @Override
    public void deleteAsAncestor(String relationType, Long ancestorId) {
        this.repository.deleteAsAncestor(relationType, ancestorId);
    }

    /**
     * @see EntityRelationManager#deleteAsChild(String, Long)
     */
    @Override
    public void deleteAsChild(String relationType, Long entityId) {
        this.repository.deleteAsChild(relationType, entityId);
    }

    /**
     * @see EntityRelationManager#clearCache()
     */
    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = SystemConstants.CACHE_PARENT_ENTITY_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = SystemConstants.CACHE_DIRECT_PARENT_ENTITY_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = SystemConstants.CACHE_CHILD_ENTITY_RELATION, beforeInvocation = true),
            @CacheEvict(cacheNames = SystemConstants.CACHE_DIRECT_CHILD_ENTITY_RELATION, beforeInvocation = true)
    })
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_PARENT_ENTITY_RELATION);
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_DIRECT_PARENT_ENTITY_RELATION);
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_CHILD_ENTITY_RELATION);
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_DIRECT_CHILD_ENTITY_RELATION);
        }
    }

}
