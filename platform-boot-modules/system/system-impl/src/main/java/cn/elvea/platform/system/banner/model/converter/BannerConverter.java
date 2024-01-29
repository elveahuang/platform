package cn.elvea.platform.system.banner.model.converter;

import cn.elvea.platform.system.banner.model.entity.BannerEntity;
import cn.elvea.platform.system.banner.model.form.BannerForm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface BannerConverter {

    BannerConverter INSTANCE = Mappers.getMapper(BannerConverter.class);

    BannerEntity formToEntity(BannerForm form);

}
