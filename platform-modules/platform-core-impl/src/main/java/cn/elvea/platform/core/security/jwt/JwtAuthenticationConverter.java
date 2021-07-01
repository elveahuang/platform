package cn.elvea.platform.core.security.jwt;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.exception.InvalidGrantTypeException;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JwtAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtAuthenticationConverter implements AuthenticationConverter {

    private static final Map<String, AuthenticationConverter> converters = new ConcurrentHashMap<>();

    public void addConverter(String key, AuthenticationConverter converter) {
        converters.put(key, converter);
    }

    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = ServletUtils.obtainRequestParameter(request, "grant_type");
        if (!SecurityGrantTypeEnum.isValidGrantType(grantType)) {
            throw new InvalidGrantTypeException();
        }
        AuthenticationConverter converter = converters.get(SecurityGrantTypeEnum.getGrantType(grantType).getValue());
        if (converter == null) {
            throw new InvalidGrantTypeException();
        }
        return converter.convert(request);
    }

}
