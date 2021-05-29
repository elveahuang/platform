package cn.elvea.platform.commons.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * JsonController
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonObject implements Serializable {
    private Long id;
    private Long[] ids;
    private List<Long> idList;
    private String code;
    private Date date;
    private Timestamp timestamp;
    private LocalDateTime localDateTime;
    private LocalDate localDate;
}
