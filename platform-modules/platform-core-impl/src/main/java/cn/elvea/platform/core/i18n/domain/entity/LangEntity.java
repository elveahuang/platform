package cn.elvea.platform.core.i18n.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

/**
 * LangTypeEntity
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_lang")
public class LangEntity extends BaseEntity {
    /**
     * 国家地区
     */
    private String locale;
    /**
     * 多语言文本
     */
    private String label;
    /**
     * 备注说明
     */
    private String description;
    /**
     * 是否默认语言
     */
    private Boolean defaultInd;
    /**
     * 启用状态
     */
    private Boolean active;
}
