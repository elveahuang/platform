package cn.elvea.platform.system.announcement.model.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementForm implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private String description;
}
