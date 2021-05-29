package cn.elvea.platform.core.security.jwt;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * JwtAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtAuthenticationConverter implements AuthenticationConverter {

    private final List<AuthenticationConverter> converters;

    public JwtAuthenticationConverter(List<AuthenticationConverter> converters) {
        Assert.notEmpty(converters, "converters cannot be empty");
        this.converters = Collections.unmodifiableList(new LinkedList<>(converters));
    }

    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {
        Assert.notNull(request, "request cannot be null");
        return this.converters.stream()
                .map(converter -> converter.convert(request))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

}
