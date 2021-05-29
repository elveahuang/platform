package cn.elvea.platform.core.i18n.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.i18n.domain.entity.LabelEntity;

/**
 * LabelManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface LabelManager extends EntityService<LabelEntity, Long> {

    LabelEntity findByCode(String code);

}
