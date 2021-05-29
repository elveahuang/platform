package cn.elvea.platform.commons.jwt;

import cn.elvea.platform.commons.utils.JwtUtils;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import java.text.ParseException;
import java.util.Map;

/**
 * JwtSecurityUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtService {

    private final JwtConfig config;

    public JwtService() {
        this(new JwtConfig());
    }

    public JwtService(JwtConfig config) {
        this.config = config;
    }

    private byte[] getJwtSecretKey() {
        return this.config.getSecret().getBytes();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // JWS
    // -----------------------------------------------------------------------------------------------------------------

    public String createJwsToken(JWTClaimsSet claimsSet) throws JOSEException {
        return JwtUtils.createJwsToken(getJwtSecretKey(), claimsSet);
    }

    public String createJwsToken(Map<String, Object> payloadMap) throws JOSEException {
        return JwtUtils.createJwsToken(getJwtSecretKey(), payloadMap);
    }

    public JWTClaimsSet parseJwsToken(String token) throws ParseException, JOSEException, BadJOSEException {
        return JwtUtils.parseJwsToken(getJwtSecretKey(), token);
    }

    public String getJwtId(String token) throws ParseException, JOSEException, BadJOSEException {
        return JwtUtils.getJwtId(getJwtSecretKey(), token);
    }

    public Map<String, Object> verifyJwsToken(String token) throws ParseException, JOSEException {
        return JwtUtils.verifyJwsToken(getJwtSecretKey(), token);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // JWT
    // -----------------------------------------------------------------------------------------------------------------

    public String createJwtToken(JWTClaimsSet claimsSet) throws JOSEException {
        return JwtUtils.createJwtToken(getJwtSecretKey(), claimsSet);
    }

    public JWTClaimsSet verifyJwtToken(String token) throws ParseException, JOSEException {
        return JwtUtils.verifyJwtToken(getJwtSecretKey(), token);
    }

    public JwtConfig getConfig() {
        return config;
    }

}
