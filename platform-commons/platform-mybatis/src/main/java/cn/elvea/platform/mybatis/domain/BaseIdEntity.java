package cn.elvea.platform.mybatis.domain;

import cn.elvea.platform.base.domain.IdEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * BaseIdEntity
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
public abstract class BaseIdEntity implements IdEntity {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
}
