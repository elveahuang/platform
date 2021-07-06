package cn.elvea.platform.core.system.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


/**
 * EntityRelationEntity
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_entity_relation")
public class EntityRelationEntity extends BaseEntity {
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 祖先ID
     */
    private Long ancestorId;
    /**
     * 实体ID
     */
    private Long entityId;
    /**
     * 关联类型
     */
    private String relationType;
    /**
     * 是否直接上级
     */
    private Boolean parentInd;
    /**
     * 层级路径
     */
    private String path_;
    /**
     * 层级序号
     */
    private Integer index_;
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
}
