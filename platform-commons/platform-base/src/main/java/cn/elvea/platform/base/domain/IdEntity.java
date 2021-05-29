package cn.elvea.platform.base.domain;

import java.io.Serializable;

/**
 * IdEntity
 *
 * @author elvea
 * @since 0.0.1
 */
public interface IdEntity extends Serializable {

    /**
     * @return Long
     */
    Long getId();

    /**
     * @param id Long
     */
    void setId(Long id);

}
