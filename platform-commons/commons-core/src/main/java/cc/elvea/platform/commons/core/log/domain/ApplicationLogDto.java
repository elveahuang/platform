package cc.elvea.platform.commons.core.log.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationLogDto implements Serializable {
    private String type;
    private String action;
    private String details;
    private String exception;
}
