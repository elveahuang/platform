package cn.elvea.platform.commons.core.data.jdbc.domain;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Id;

/**
 * @author elvea
 * @see IdEntity
 * @since 24.1.0
 */
public abstract class AbstractEntity implements IdEntity {

    protected Long id;

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
