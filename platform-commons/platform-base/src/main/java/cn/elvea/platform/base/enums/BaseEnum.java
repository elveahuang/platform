package cn.elvea.platform.base.enums;

import java.io.Serializable;

/**
 * 基础枚举
 *
 * @author elvea
 * @since 0.0.1
 */
public interface BaseEnum<T extends Serializable> {

    T getValue();

    String getLabel();

    String getDescription();

}
