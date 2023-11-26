package cn.elvea.platform.system.commons.api.impl;

import cn.elvea.platform.commons.core.extensions.keyword.KeywordManager;
import cn.elvea.platform.system.commons.api.KeywordApi;
import cn.elvea.platform.system.core.model.entity.KeywordEntity;
import cn.elvea.platform.system.core.service.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Service
@AllArgsConstructor
public class KeywordApiImpl implements KeywordApi {

    private final KeywordService keywordService;

    private final KeywordManager keywordManager;

    /**
     * @see KeywordApi#initialize()
     */
    @Override
    public void initialize() {
        keywordManager.initialize(keywordService.findAll().stream().map(KeywordEntity::getContent).toList());
    }

}
