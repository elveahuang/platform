package cn.elvea.platform.xapi.entity;

import cn.elvea.platform.xapi.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * StatementEntity
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "olxp_statement")
public class StatementEntity implements Serializable {
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * Stored
     */
    private DateTime stored;
    /**
     * Authority
     */
    private Agent authority;
    /**
     * Version
     */
    private String version;
    /**
     *
     */
    private Actor actor;
    /**
     *
     */
    private Verb verb;
    /**
     *
     */
    private AbstractObject object;
    /**
     *
     */
    private Result result;
    /**
     *
     */
    private Context context;
    /**
     *
     */
    private DateTime timestamp;
    /**
     *
     */
    private List<Attachment> attachments;
}
