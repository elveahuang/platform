package cn.elvea.platform.core.i18n.manager.impl;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.i18n.domain.entity.LabelEntity;
import cn.elvea.platform.core.i18n.manager.LabelManager;
import cn.elvea.platform.core.i18n.repository.LangLabelRepository;
import org.springframework.stereotype.Service;

/**
 * LabelManager
 *
 * @author elvea
 * @see LabelManager
 * @since 0.0.1
 */
@Service
public class LabelManagerImpl extends AbstractEntityService<LabelEntity, Long, LangLabelRepository> implements LabelManager {

    @Override
    public LabelEntity findByCode(String code) {
        return this.repository.findByCode(code);
    }

}
