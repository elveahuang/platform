package cn.elvea.platform.system.tag.model.entity;

import cn.elvea.platform.commons.core.data.jpa.domain.SimpleEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class TagRelationEntity extends SimpleEntity {
    /**
     * 标签类型ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long typeId;
    /**
     * 标签ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;
    /**
     * 目标id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long targetId;
    /**
     * 目标类型
     */
    private String targetType;
}
