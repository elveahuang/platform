package cn.elvea.platform.system.tag.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.tag.model.entity.TagEntity;
import cn.elvea.platform.system.tag.model.entity.TagEntity_;
import cn.elvea.platform.system.tag.repository.TagRepository;
import cn.elvea.platform.system.tag.service.TagService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class TagServiceImpl
        extends BaseCachingEntityService<TagEntity, Long, TagRepository>
        implements TagService {

    /**
     * @see TagService#findByTypeId(Long)
     */
    @Override
    public List<TagEntity> findByTypeId(Long tagTypeId) {
        Specification<TagEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(TagEntity_.TYPE_ID), tagTypeId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

}
