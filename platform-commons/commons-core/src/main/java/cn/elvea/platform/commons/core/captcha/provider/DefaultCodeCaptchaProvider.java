package cn.elvea.platform.commons.core.captcha.provider;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.captcha.Captcha;
import cn.elvea.platform.commons.core.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

/**
 * @author elvea
 * @since 24.1.0
 */
public class DefaultCodeCaptchaProvider implements CodeCaptchaProvider {

    @Override
    public Captcha generate(CaptchaRequest request) {
        int length = (request.getSize() <= 0) ? 4 : request.getSize();
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(160, 24, length, 16);
        lineCaptcha.getImageBase64();
        return Captcha.builder().type(CaptchaTypeEnum.CODE)
                .key(StringUtils.uuid())
                .value(lineCaptcha.getCode())
                .image(lineCaptcha.getImageBase64Data())
                .build();
    }

}
