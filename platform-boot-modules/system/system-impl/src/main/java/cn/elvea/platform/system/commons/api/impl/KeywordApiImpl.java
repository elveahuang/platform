package cn.elvea.platform.system.commons.api.impl;

import cn.elvea.platform.commons.core.keyword.KeywordManager;
import cn.elvea.platform.system.commons.api.KeywordApi;
import cn.elvea.platform.system.keyword.model.entity.KeywordEntity;
import cn.elvea.platform.system.keyword.service.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 24.1.0
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
