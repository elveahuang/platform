package cn.elvea.platform.commons.core.oapis.sms;

import cn.elvea.platform.commons.core.oapis.sms.aliyun.AliyunSmsSender;
import cn.elvea.platform.commons.core.oapis.sms.tencent.TencentSmsSender;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
public class SmsServer implements Serializable {

    @Builder.Default
    private AliyunSmsSender.ServerConfig aliyun = AliyunSmsSender.ServerConfig.builder().build();

    @Builder.Default
    private TencentSmsSender.ServerConfig tencent = TencentSmsSender.ServerConfig.builder().build();

}
