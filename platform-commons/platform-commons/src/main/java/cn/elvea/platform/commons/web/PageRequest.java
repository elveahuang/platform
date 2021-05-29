package cn.elvea.platform.commons.web;

import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 分页请求
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageRequest<T> extends Request {
    /**
     * 查询对象
     */
    private T model;
    /**
     * 当前页
     */
    @Schema(title = "页码", defaultValue = "1")
    private int page = 1;
    /**
     * 每页显示多少条记录
     */
    @Schema(title = "记录数", defaultValue = "10")
    private int size = 10;
    /**
     * 排序
     */
    @Schema(title = "排序字段", description = "id", example = "id")
    private String sort;
    /**
     * 排序规则
     */
    @Schema(title = "记录数", defaultValue = "10")
    private String order;
    /**
     * 搜索关键字
     */
    @Schema(title = "搜索关键字")
    private String q;
    /**
     * 扩展参数
     */
    @Schema(title = "扩展参数")
    private Map<String, String> extra = Maps.newHashMap();
}
