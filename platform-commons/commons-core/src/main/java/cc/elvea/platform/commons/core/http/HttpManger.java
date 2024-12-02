package cc.elvea.platform.commons.core.http;

import cc.elvea.platform.commons.core.http.enums.HttpClientTypeEnum;
import lombok.Getter;

/**
 * @author elvea
 * @since 24.1.0
 */
public class HttpManger {

    @Getter
    private static volatile HttpFactory http = HttpFactory.builder().type(HttpClientTypeEnum.OKHTTP).build();

    public static void setHttp(HttpFactory http) {
        HttpManger.http = http;
    }

}
