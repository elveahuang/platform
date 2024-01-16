package cn.elvea.platform.commons.core.data.mybatis.domain.event;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
public interface PreSuperBuilderDeleteEvent {

    LocalDateTime getDeletedAt();

    void setDeletedAt(LocalDateTime deletedAt);

    Long getDeletedBy();

    void setDeletedBy(Long deletedBy);
}
