package cn.elvea.platform.commons.oapis.weixin.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.elvea.platform.commons.oapis.weixin.config.AppMaConfig;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface WeiXinMaService {

    WxMaConfig getConfigStorage();

    WxMaConfig getConfigStorage(AppMaConfig appConfig);

    WxMaService getService();

    WxMaService getService(AppMaConfig appConfig);

}
