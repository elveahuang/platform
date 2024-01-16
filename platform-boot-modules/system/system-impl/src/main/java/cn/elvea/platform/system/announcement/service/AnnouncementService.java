package cn.elvea.platform.system.announcement.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cn.elvea.platform.system.announcement.model.form.AnnouncementForm;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface AnnouncementService extends CachingEntityService<AnnouncementEntity, Long> {

    void saveAnnouncement(AnnouncementForm form);

}
