package cn.elvea.platform.mongo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;

/**
 * MongoTestApplicationBaseTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class MongoTestApplicationBaseTests extends MongoTestApplicationTests {

    protected final MongoDatabaseFactory mongoDatabaseFactory;

    @Autowired
    protected MongoTestApplicationBaseTests(MongoDatabaseFactory mongoDatabaseFactory) {
        this.mongoDatabaseFactory = mongoDatabaseFactory;
    }

    @Test
    public void test() {
        Assertions.assertNotNull(this.mongoDatabaseFactory);
    }

}
