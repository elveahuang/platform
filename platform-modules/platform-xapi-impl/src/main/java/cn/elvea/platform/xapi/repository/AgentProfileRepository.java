package cn.elvea.platform.xapi.repository;

import cn.elvea.platform.xapi.entity.AgentProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * AgentProfileRepository
 *
 * @author elvea
 */
public interface AgentProfileRepository extends MongoRepository<AgentProfileEntity, String> {
}
