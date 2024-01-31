package cn.elvea.platform.system.log.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.commons.core.logging.domain.UrlStatLogDto;
import cn.elvea.platform.system.log.model.entity.UrlStatLogEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface UrlStatLogRepository extends BaseEntityRepository<UrlStatLogEntity, Long> {

    @Query("""
            SELECT new cn.elvea.platform.commons.core.logging.domain.UrlStatLogDto(t.path, avg(t.execTime), max(t.execTime), min(t.execTime), sum(t.execTime), count(t.id))
            FROM UrlLogEntity t
            where t.path = :path
            """)
    UrlStatLogDto getStatData(@Param("path") String path);

}
