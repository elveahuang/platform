package cn.elvea.platform.system.mall.web.callback;

import cn.elvea.platform.commons.core.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 24.1.0
 */
@RestController
@AllArgsConstructor
@Tag(name = "WxPayCallbackController", description = "微信支付回调接口")
public class WxPayCallbackController extends AbstractController {
}
