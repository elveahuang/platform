package cn.elvea.platform.commons.web.controller;

/**
 * 顶层抽象控制器
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractController {

    /**
     * 重定向
     *
     * @param url URL
     * @return 跳转地址
     */
    protected String redirect(String url) {
        return "redirect:".concat(url);
    }

}
