package cn.elvea.platform.xapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * ActivityProfileEntity
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "olxp_activity_profile")
public class ActivityProfileEntity implements Serializable {
    /**
     * ID
     */
    @Id
    private String id;
    /**
     *
     */
    private String activityId;
    /**
     *
     */
    private String profileId;
    /**
     *
     */
    private String content;
    /**
     * 是否启用
     */
    private Boolean active;
    /**
     * 创建人
     */
    private Date createdBy;
    /**
     * 创建日期
     */
    private Date createdAt;
    /**
     * 更新人
     */
    private Date updatedBy;
    /**
     * 更新日期
     */
    private Date updatedAt;
    /**
     * 删除人
     */
    private Date deleteBy;
    /**
     * 删除日期
     */
    private Date deleteAt;
}
