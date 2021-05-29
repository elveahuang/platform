package cn.elvea.platform.core.i18n.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * LangLabelEntity
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_label")
public class LabelEntity extends BaseEntity {
    /**
     * 多语言标识
     */
    private String code;
    /**
     * 备注说明
     */
    private String description;
    /**
     * 简体中文
     */
    private String zhCnLabel;
    private Boolean zhCnSourceInd;
    private Boolean zhCnFinalInd;
    /**
     * 繁体中文
     */
    private String zhHkLabel;
    private Boolean zhHkSourceInd;
    private Boolean zhHkFinalInd;
    /**
     * 美式英语
     */
    private String enUsLabel;
    private Boolean enUsSourceInd;
    private Boolean enUsFinalInd;
    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @CreatedBy
    private Long createdBy;
    /**
     * 最后修改时间
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    /**
     * 最后修改人
     */
    @LastModifiedBy
    private Long lastModifiedBy;
}
