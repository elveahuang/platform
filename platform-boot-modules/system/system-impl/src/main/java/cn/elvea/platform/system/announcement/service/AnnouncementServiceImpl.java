package cn.elvea.platform.system.announcement.service;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.enums.PublishStatusTypeEnum;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.system.announcement.model.converter.AnnouncementConverter;
import cn.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cn.elvea.platform.system.announcement.model.form.SystemAnnouncementForm;
import cn.elvea.platform.system.announcement.model.request.AnnouncementSearchRequest;
import cn.elvea.platform.system.announcement.model.request.SystemAnnouncementSearchRequest;
import cn.elvea.platform.system.announcement.repository.AnnouncementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
public class AnnouncementServiceImpl
        extends BaseCachingEntityService<AnnouncementEntity, Long, AnnouncementRepository>
        implements AnnouncementService {

    /**
     * @see AnnouncementService#saveAnnouncement(SystemAnnouncementForm)
     */
    @Override
    public Page<?> findByPage(SystemAnnouncementSearchRequest request) {
        AnnouncementEntity example = AnnouncementEntity.builder().build();
        example.setActive(Boolean.TRUE);
        return findByPage(request.getPageable(), example);
    }

    /**
     * @see AnnouncementService#saveAnnouncement(SystemAnnouncementForm)
     */
    @Override
    public Page<?> findByPage(AnnouncementSearchRequest request) {
        AnnouncementEntity example = AnnouncementEntity.builder().build();
        example.setActive(Boolean.TRUE);
        example.setStatus(PublishStatusTypeEnum.ON.getValue());
        return findByPage(request.getPageable(), example);
    }

    /**
     * @see AnnouncementService#saveAnnouncement(SystemAnnouncementForm)
     */
    @Override
    public void saveAnnouncement(SystemAnnouncementForm form) {
        AnnouncementEntity entity;
        if (form.getId() != null && form.getId() > 0) {
            entity = this.findById(form.getId());
            ObjectUtils.copyNotNullProperties(form, entity);
        } else {
            entity = AnnouncementConverter.INSTANCE.formToEntity(form);
        }
        entity.setActive(Boolean.TRUE);
        this.save(entity);
    }

}
