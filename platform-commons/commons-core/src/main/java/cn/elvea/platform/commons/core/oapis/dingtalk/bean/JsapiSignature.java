package cn.elvea.platform.commons.core.oapis.dingtalk.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsapiSignature implements Serializable {

    private String agentId;

    private String corpId;

    private String nonceStr;

    private long timestamp;

    private String url;

    private String signature;

}
