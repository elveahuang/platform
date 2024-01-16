package cn.elvea.platform.system.announcement.mapper;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.announcement.domain.entity.AnnouncementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
@Repository
public interface AnnouncementMapper extends BaseEntityMapper<AnnouncementEntity, Long> {
}
