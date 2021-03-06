package cn.elvea.platform.core.report.controller;

import cn.elvea.platform.commons.web.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ReportController
 *
 * @since elvea
 * @since 0.0.1
 */
@Controller
@RequestMapping("/report")
public class ReportController extends AbstractReportController {

    /**
     * 查看报表
     */
    @ResponseBody
    @PostMapping("/{report}/result")
    public Response<?> result(@PathVariable("report") String report) {
        return Response.success();
    }

    /**
     * 导出报表
     */
    @ResponseBody
    @PostMapping("/{report}/export")
    public Response<?> export(@PathVariable("report") String report) {
        return Response.success();
    }

    /**
     * 获取报表模板
     */
    @ResponseBody
    @GetMapping("/{report}/template")
    public Response<?> getTemplate(@PathVariable("report") String report) {
        return Response.success();
    }

    /**
     * 获取报表模板
     */
    @ResponseBody
    @GetMapping("/{report}/template/list")
    public Response<?> listTemplate(@PathVariable("report") String report) {
        return Response.success();
    }

    /**
     * 保存报表模板
     */
    @ResponseBody
    @PostMapping("/{report}/template/save")
    public Response<?> saveTemplate(@PathVariable("report") String report) {
        return Response.success();
    }

}
