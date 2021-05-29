package cn.elvea.platform.core.i18n.support;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * LabelExcelRecord
 *
 * @author elvea
 * @since 0.01
 */
@Data
@NoArgsConstructor
public class LabelExcelRecord implements Serializable {
    @ExcelProperty("编号")
    private String code;
    @ExcelProperty("简体中文")
    private String zhCnLabel;
    @ExcelProperty("繁体中文")
    private String zhHkLabel;
    @ExcelProperty("美式英语")
    private String enUsLabel;
}
