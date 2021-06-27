package cn.elvea.platform.elastic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * ElasticTestApplicationBaseTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class ElasticTestApplicationBaseTests extends ElasticTestApplicationTests {

    private final ElasticsearchRestTemplate template;

    @Autowired
    public ElasticTestApplicationBaseTests(ElasticsearchRestTemplate template) {
        this.template = template;
    }

    @Test
    public void test() {
        Assertions.assertNotNull(this.template);
    }

}
