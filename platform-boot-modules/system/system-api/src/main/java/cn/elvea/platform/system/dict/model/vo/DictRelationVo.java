package cn.elvea.platform.system.dict.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DictRelationVo implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 字典类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * 字典明细ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long itemId;
}
