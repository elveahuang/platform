package cn.elvea.platform.elastic.service;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * BaseService
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class BaseService {

    protected final ElasticsearchRestTemplate template;

    public BaseService(ElasticsearchRestTemplate template) {
        this.template = template;
    }

}
