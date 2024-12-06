package cc.elvea.platform.system.log.model.converter;

import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.elvea.platform.system.log.model.entity.CaptchaLogEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper(componentModel = SPRING)
public interface CaptchaLogConverter {

    CaptchaLogEntity dto2Entity(CaptchaLogDto dto);

}
