package cn.elvea.platform.core.report.controller;

import cn.elvea.platform.commons.web.Response;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * BaseReportController
 *
 * @since elvea
 * @since 0.0.1
 */
public abstract class BaseReportController extends AbstractReportController {

    /**
     * 报表条件页面
     */
    @GetMapping("/{report}/view")
    public String view(Model model, @PathVariable("report") String report) {
        this.prepareResultView(model);
        return getView();
    }

    /**
     * 报表结果页面
     */
    @GetMapping("/{report}/result/view")
    public String resultView(@PathVariable("report") String report) {
        return getResultView();
    }

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
