package cn.elvea.platform.commons.core.extensions.captcha.provider;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

/**
 * @author elvea
 * @since 0.0.1
 */
public class DefaultCodeCaptchaProvider implements CodeCaptchaProvider {

    @Override
    public Captcha generate(CaptchaRequest request) {
        int length = (request.getSize() <= 4) ? 4 : request.getSize();
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, length, 32);
        return Captcha.builder().type(CaptchaTypeEnum.CODE)
                .key(StringUtils.uuid())
                .value(lineCaptcha.getCode())
                .image(lineCaptcha.getImageBase64Data())
                .build();
    }

}
