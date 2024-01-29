package cn.elvea.platform.system.announcement.model.converter;

import cn.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cn.elvea.platform.system.announcement.model.form.SystemAnnouncementForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface AnnouncementConverter {

    AnnouncementConverter INSTANCE = Mappers.getMapper(AnnouncementConverter.class);

    AnnouncementEntity formToEntity(SystemAnnouncementForm form);

}
