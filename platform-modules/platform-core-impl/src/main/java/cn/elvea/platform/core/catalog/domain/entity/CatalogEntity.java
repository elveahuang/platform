package cn.elvea.platform.core.catalog.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 目录分类
 *
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("sys_catalog")
public class CatalogEntity extends BaseEntity {
    /**
     * 编号
     */
    private String code;
    /**
     * 启用状态
     */
    private Boolean active;
}
