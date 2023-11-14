package cn.elvea.platform.commons.core.extensions.captcha.provider;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class DefaultMailCaptchaProvider implements MailCaptchaProvider {

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        int length = (request.getSize() <= 6) ? 6 : request.getSize();
        String number = StringUtils.randomNumeric(length);
        return Captcha.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(request.getEmail())
                .key(StringUtils.uuid())
                .value(number).build();
    }

}
