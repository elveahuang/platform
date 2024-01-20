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
@Table(name = "sys_tag")
@DynamicUpdate
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class TagEntity extends BaseEntity {
    /**
     * 类型id
     */
    private Long tagTypeId;
    /**
     * 文本
     */
    private String title;
    /**
     * 备注
     */
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
