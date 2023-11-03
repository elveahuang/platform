package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseEntityService;
import cn.elvea.platform.system.message.model.entity.NoticeEntity;
import cn.elvea.platform.system.message.model.entity.NoticeEntity_;
import cn.elvea.platform.system.message.repository.NoticeRepository;
import cn.elvea.platform.system.message.request.NoticeSearchRequest;
import cn.elvea.platform.system.message.service.NoticeService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Service
public class NoticeServiceImpl
        extends BaseEntityService<NoticeEntity, Long, NoticeRepository>
        implements NoticeService {

    @Override
    public Page<NoticeEntity> findByUserId(NoticeSearchRequest request) {
        Specification<NoticeEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = org.apache.commons.compress.utils.Lists.newArrayList();
            if (request.getUserId() != null && request.getUserId() > 0) {
                predicates.add(builder.equal(root.get(NoticeEntity_.recipientId), request.getUserId()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification, request.getPageable());
    }

}
