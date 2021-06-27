package cn.elvea.platform.mongo.service;

import org.springframework.data.mongodb.MongoDatabaseFactory;

/**
 * BaseService
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class BaseService {

    protected final MongoDatabaseFactory mongo;

    public BaseService(MongoDatabaseFactory mongo) {
        this.mongo = mongo;
    }

}
