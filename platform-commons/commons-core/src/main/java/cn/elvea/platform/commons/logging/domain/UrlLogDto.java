package cn.elvea.platform.commons.logging.domain;

import cn.elvea.platform.commons.constants.DateTimeConstants;
import cn.elvea.platform.commons.annotations.DateTimeFormat;
import cn.elvea.platform.commons.annotations.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UrlLogDto implements Serializable {
    /**
     * 路径
     */
    private String path;
    /**
     * 日志的开始时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime startTime;
    /**
     * 日志的开始时间
     */
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime endTime;
    /**
     * 执行时长
     */
    private Long execTime;
}
