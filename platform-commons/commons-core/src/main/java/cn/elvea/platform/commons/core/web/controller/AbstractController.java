package cn.elvea.platform.commons.core.web.controller;

import cn.elvea.platform.commons.core.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 顶层抽象控制器
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractController {

    protected Context context;

    protected String redirect(String url) {
        return String.format("redirect:%s", url);
    }

    protected String forward(String url) {
        return String.format("forward:%s", url);
    }

    @Autowired
    public void setContext(Context context) {
        this.context = context;
    }

}
