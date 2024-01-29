package cn.elvea.platform.system.announcement.model.form;

import cn.elvea.platform.system.dict.model.vo.DictRelationVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemAnnouncementForm implements Serializable {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
    /**
     * 标题
     */
    @NotEmpty
    private String title;
    /**
     * 内容
     */
    @NotEmpty
    private String content;
    /**
     * 备注
     */
    private String description;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 类型
     */
    private DictRelationVo type;
}
