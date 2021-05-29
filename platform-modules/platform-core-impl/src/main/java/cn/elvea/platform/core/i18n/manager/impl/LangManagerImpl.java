package cn.elvea.platform.core.i18n.manager.impl;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.i18n.domain.entity.LangEntity;
import cn.elvea.platform.core.i18n.manager.LangManager;
import cn.elvea.platform.core.i18n.repository.LangTypeRepository;
import org.springframework.stereotype.Service;

/**
 * LangManager
 *
 * @author elvea
 * @see LangManager
 * @since 0.0.1
 */
@Service
public class LangManagerImpl extends AbstractEntityService<LangEntity, Long, LangTypeRepository> implements LangManager {
}
