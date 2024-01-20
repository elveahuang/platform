package cn.elvea.platform.system.tag.model.entity;

import cn.elvea.platform.commons.core.data.jpa.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author elvea
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_tag_relation")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class TagRelationEntity extends BaseEntity {
    /**
     * 标签类型id
     */
    private Long tagTypeId;
    /**
     * 标签id
     */
    private Long tagId;
    /**
     * 目标id
     */
    private Long targetId;
    /**
     * 目标类型
     */
    private String targetType;
}
