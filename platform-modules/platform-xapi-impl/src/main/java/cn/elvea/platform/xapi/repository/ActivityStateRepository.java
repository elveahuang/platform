package cn.elvea.platform.xapi.repository;

import cn.elvea.platform.xapi.entity.ActivityStateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ActivityStateRepository
 *
 * @author elvea
 */
@Repository
public interface ActivityStateRepository extends MongoRepository<ActivityStateEntity, String> {
}
