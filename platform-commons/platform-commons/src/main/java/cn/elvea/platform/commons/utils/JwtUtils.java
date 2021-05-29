package cn.elvea.platform.commons.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JwtUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class JwtUtils {

    public final static JWSAlgorithm JWT_ALGORITHM = JWSAlgorithm.HS256;

    public final static String JWT_SECRET = "TTaVQKspIXJqOsmvIbNCGpPgGNqEjphh";

    public final static byte[] JWT_SECRET_KEY = JWT_SECRET.getBytes();

    public static final String JWT_KEY_UID = "uid";

    public static final String JWT_KEY_ACCOUNT = "account";

    public static final String JWT_KEY_TOKEN_TYPE = "type";

    public static final String JWT_TOKEN_TYPE_ACCESS_TOKEN = "access_token";

    public static final String JWT_TOKEN_TYPE_REFRESH_TOKEN = "refresh_token";

    public final static String BEARER_PREFIX = "Bearer ";

    public static final String AUTHORIZATION = "Authorization";

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        return gen.generateKeyPair();
    }

    public static RSAKey generateRsaKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
    }

    public static JWKSource<SecurityContext> generateJWKSource() throws Exception {
        JWKSet jwkSet = new JWKSet(generateRsaKey());
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // JWS
    // -----------------------------------------------------------------------------------------------------------------

    public static String createJwsToken(byte[] secretKey, Map<String, Object> payloadMap) throws JOSEException {
        JWSHeader header = new JWSHeader(JWT_ALGORITHM);
        JWSSigner signer = new MACSigner(secretKey);
        Payload payload = new Payload(new JSONObject(payloadMap));
        JWSObject object = new JWSObject(header, payload);
        object.sign(signer);
        return object.serialize();
    }

    public static String createJwsToken(byte[] secretKey, JWTClaimsSet claimsSet) throws JOSEException {
        return createJwsToken(secretKey, claimsSet.toJSONObject());
    }

    public static String createJwsToken(JWTClaimsSet claimsSet) throws JOSEException {
        return createJwsToken(JWT_SECRET_KEY, claimsSet);
    }

    public static String createJwsToken(Map<String, Object> payloadMap) throws JOSEException {
        return createJwsToken(JWT_SECRET_KEY, payloadMap);
    }

    public static JWTClaimsSet parseJwsToken(byte[] secretKey, String token) throws ParseException, JOSEException, BadJOSEException {
        ConfigurableJWTProcessor<SecurityContext> processor = new DefaultJWTProcessor<>();
        JWKSource<SecurityContext> source = new ImmutableSecret<>(secretKey);
        JWSKeySelector<SecurityContext> selector = new JWSVerificationKeySelector<>(JWT_ALGORITHM, source);
        processor.setJWSKeySelector(selector);
        SecurityContext context = new SimpleSecurityContext();
        return processor.process(token, context);
    }

    public static JWTClaimsSet parseJwsToken(String token) throws ParseException, JOSEException, BadJOSEException {
        return parseJwsToken(JWT_SECRET_KEY, token);
    }

    public static String getJwtId(byte[] secretKey, String token) throws ParseException, JOSEException, BadJOSEException {
        JWTClaimsSet claimsSet = parseJwsToken(secretKey, token);
        return claimsSet.getJWTID();
    }

    public static String getJwtId(String token) throws ParseException, JOSEException, BadJOSEException {
        return getJwtId(JWT_SECRET_KEY, token);
    }

    public static Map<String, Object> verifyJwsToken(byte[] secretKey, String token) throws ParseException, JOSEException {
        JWSObject object = JWSObject.parse(token);
        Payload payload = object.getPayload();
        JWSVerifier verifier = new MACVerifier(secretKey);
        if (object.verify(verifier)) {
            return payload.toJSONObject();
        }
        return new HashMap<>();
    }

    public static Map<String, Object> verifyJwsToken(String token) throws ParseException, JOSEException {
        return verifyJwsToken(JWT_SECRET_KEY, token);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // JWT
    // -----------------------------------------------------------------------------------------------------------------

    public static String createJwtToken(byte[] secretKey, JWTClaimsSet claimsSet) throws JOSEException {
        JWSHeader header = new JWSHeader(JWT_ALGORITHM);
        JWSSigner signer = new MACSigner(secretKey);
        SignedJWT jwt = new SignedJWT(header, claimsSet);
        jwt.sign(signer);
        return jwt.serialize();
    }

    public static String createJwtToken(JWTClaimsSet claimsSet) throws JOSEException {
        return createJwtToken(JWT_SECRET_KEY, claimsSet);
    }

    public static JWTClaimsSet verifyJwtToken(byte[] secretKey, String token) throws ParseException, JOSEException {
        SignedJWT jwt = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(secretKey);
        if (jwt.verify(verifier)) {
            return jwt.getJWTClaimsSet();
        }
        return null;
    }

    public static JWTClaimsSet verifyJwtToken(String token) throws ParseException, JOSEException {
        return verifyJwtToken(JWT_SECRET_KEY, token);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 辅助方法
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 生产随机字符串
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String generateSecret(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String getAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith(BEARER_PREFIX)) {
            return authorization.replace(BEARER_PREFIX, "");
        }
        return null;
    }

    public static String getRefreshToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith(BEARER_PREFIX)) {
            return authorization.replace(BEARER_PREFIX, "");
        }
        return null;
    }

}
