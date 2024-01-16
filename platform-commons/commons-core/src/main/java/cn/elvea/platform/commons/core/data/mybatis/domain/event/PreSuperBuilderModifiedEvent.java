package cn.elvea.platform.commons.core.data.mybatis.domain.event;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
public interface PreSuperBuilderModifiedEvent {

    LocalDateTime getLastModifiedAt();

    void setLastModifiedAt(LocalDateTime updatedAt);

    Long getLastModifiedBy();

    void setLastModifiedBy(Long updatedBy);
}
