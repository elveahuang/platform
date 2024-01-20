package cn.elvea.platform.system.dict.model.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DictForm implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 字典ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagTypeId;
    /**
     * 文本
     */
    @NotEmpty
    private String title;
    /**
     * 备注
     */
    @NotEmpty
    private String description;
    /**
     * 序号
     */
    private Integer sortOrder;
    /**
     * 来源
     */
    private Integer source;
}
