package cn.elvea.platform.system.announcement.service;

import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cn.elvea.platform.system.announcement.model.form.SystemAnnouncementForm;
import cn.elvea.platform.system.announcement.model.request.AnnouncementSearchRequest;
import cn.elvea.platform.system.announcement.model.request.SystemAnnouncementSearchRequest;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface AnnouncementService extends CachingEntityService<AnnouncementEntity, Long> {

    /**
     * 前端分页查询
     */
    Page<?> findByPage(AnnouncementSearchRequest request);

    /**
     * 后端分页查询
     */
    Page<?> findByPage(SystemAnnouncementSearchRequest request);

    /**
     * 保存公告
     */
    void saveAnnouncement(SystemAnnouncementForm form);

}
