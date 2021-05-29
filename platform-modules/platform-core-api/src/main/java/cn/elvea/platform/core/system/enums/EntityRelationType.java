package cn.elvea.platform.core.system.enums;

import cn.elvea.platform.base.enums.BaseEnum;
import lombok.Getter;

/**
 * 实体关联类型
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum EntityRelationType implements BaseEnum<String> {
    DPT_PARENT_DPT("DPT_PARENT_DPT", "部门和部门关联"),
    PST_PARENT_PST("PST_PARENT_PST", "岗位和岗位关联"),
    USR_CURRENT_DPT("USR_CURRENT_DPT", "用户和部门关联"),
    USR_CURRENT_PST("USR_CURRENT_PST", "用户和岗位关联");

    private final String code;
    private final String description;

    EntityRelationType(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_entity_relation_type__" + this.code.toLowerCase();
    }

}
