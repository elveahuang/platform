package cn.elvea.platform.commons.core.data.mybatis.domain.event;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
public interface PreSuperBuilderCreateEvent {

    LocalDateTime getCreatedAt();

    void setCreatedAt(LocalDateTime createdAt);

    Long getCreatedBy();

    void setCreatedBy(Long createdBy);
}
