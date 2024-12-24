package cc.elvea.platform.system.announcement.model.converter;

import cc.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cc.elvea.platform.system.announcement.model.form.AnnouncementForm;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper(componentModel = SPRING)
public interface AnnouncementConverter {

    AnnouncementEntity formToEntity(AnnouncementForm form);

}
