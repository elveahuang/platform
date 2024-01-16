package cn.elvea.platform.system.announcement.service.impl;

import cn.elvea.platform.commons.core.data.mybatis.service.BaseEntityService;
import cn.elvea.platform.system.announcement.domain.entity.AnnouncementEntity;
import cn.elvea.platform.system.announcement.mapper.AnnouncementMapper;
import cn.elvea.platform.system.announcement.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see AnnouncementService
 * @since 24.1.0
 */
@Slf4j
@Service
public class AnnouncementServiceImpl
        extends BaseEntityService<AnnouncementEntity, Long, AnnouncementMapper>
        implements AnnouncementService {
}
