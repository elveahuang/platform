package cn.elvea.platform.core.report.controller;

import cn.elvea.platform.commons.web.controller.AbstractController;
import cn.elvea.platform.core.report.ReportRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

/**
 * AbstractReportController
 *
 * @since elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class AbstractReportController extends AbstractController {

    /**
     * 获取报表条件页面
     */
    protected String getView() {
        return "";
    }

    /**
     * 准备报表条件页面
     */
    protected void prepareView(Model model) {
        // 默认没有实现代码
    }

    /**
     * 获取报表结果页面
     */
    protected String getResultView() {
        return "";
    }

    /**
     * 准备报表结果页面
     */
    protected void prepareResultView(Model model) {
        // 默认没有实现代码
    }

    /**
     * 导出报表
     */
    protected <C extends ReportRequest> void doExport(C reportRequest) {
        // 默认没有实现代码
    }

    protected <C extends ReportRequest> void beforeExport(C reportCondition) {
        // 默认没有实现代码
    }

    protected <C extends ReportRequest> void doGetResult(Model model, C reportRequest) {
        // 默认没有实现代码
    }

}
