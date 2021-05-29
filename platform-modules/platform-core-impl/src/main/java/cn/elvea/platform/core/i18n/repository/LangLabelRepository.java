package cn.elvea.platform.core.i18n.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.i18n.domain.entity.LabelEntity;
import org.springframework.stereotype.Repository;

/**
 * LangLabelRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface LangLabelRepository extends JdbcRepository<LabelEntity, Long> {

    /**
     * 根据编号获取多语言文本记录
     */
    LabelEntity findByCode(String code);

}
