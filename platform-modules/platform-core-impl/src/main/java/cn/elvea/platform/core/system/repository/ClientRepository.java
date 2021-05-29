package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.ClientEntity;
import org.springframework.stereotype.Repository;

/**
 * ClientRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface ClientRepository extends JdbcRepository<ClientEntity, Long> {

    /**
     * 根据标识查找用户
     */
    ClientEntity findByClientId(String clientId);

}
