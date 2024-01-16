package cn.elvea.platform.commons.core.data.mybatis.domain;

import cn.elvea.platform.commons.core.annotations.DateTimeFormat;
import cn.elvea.platform.commons.core.annotations.JsonFormat;
import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class SimpleSuperBuilderEntity extends AbstractSuperBuilderEntity {
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;
}
