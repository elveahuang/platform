package cn.elvea.platform.commons.core.data.mybatis.domain;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author elvea
 * @see IdEntity
 * @since 0.0.1
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractSuperBuilderEntity implements IdEntity {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
