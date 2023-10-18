package cn.elvea.platform.system.commons.api.impl;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.extensions.captcha.service.CaptchaService;
import cn.elvea.platform.system.commons.api.CaptchaApi;
import cn.elvea.platform.system.commons.constants.SystemMessageConstants;
import cn.elvea.platform.system.message.api.MessageApi;
import cn.elvea.platform.system.message.enums.MessageTargetTypeEnum;
import cn.elvea.platform.system.message.enums.MessageTemplateTypeEnum;
import cn.elvea.platform.system.message.model.dto.CreateMessageDto;
import cn.elvea.platform.system.message.utils.MessageBuilder;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
@Service
public class CaptchaApiImpl implements CaptchaApi {

    private CaptchaService captchaService;

    private MessageApi messageApi;

    @Override
    public Captcha generate(CaptchaRequest request) throws Exception {
        Captcha captcha = this.captchaService.generate(request);
        if (CaptchaTypeEnum.SMS.equals(captcha.getType())) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("code", captcha.getValue());

            CreateMessageDto message = MessageBuilder.builder()
                    .type(SystemMessageConstants.CAPTCHA_MESSAGE)
                    .templateType(MessageTemplateTypeEnum.SMS)
                    .targetType(MessageTargetTypeEnum.IMMEDIATE)
                    .sender(1L)
                    .recipient(request.getMobileCountryCode(), request.getMobileNumber())
                    .params(params)
                    .build();
            this.messageApi.createMessage(message);
        } else if (CaptchaTypeEnum.MAIL.equals(captcha.getType())) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("code", captcha.getValue());
            CreateMessageDto message = MessageBuilder.builder()
                    .type(SystemMessageConstants.CAPTCHA_MESSAGE)
                    .templateType(MessageTemplateTypeEnum.MAIL)
                    .targetType(MessageTargetTypeEnum.IMMEDIATE)
                    .sender(1L)
                    .recipient(request.getEmail())
                    .params(params)
                    .build();
            this.messageApi.createMessage(message);
        }
        return captcha;
    }

    @Autowired
    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @Autowired
    public void setMessageApi(MessageApi messageApi) {
        this.messageApi = messageApi;
    }

}