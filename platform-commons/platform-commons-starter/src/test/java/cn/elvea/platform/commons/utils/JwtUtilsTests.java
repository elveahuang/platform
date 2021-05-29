package cn.elvea.platform.commons.utils;

import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static cn.elvea.platform.commons.utils.JwtUtils.JWT_KEY_UID;

/**
 * JwtUtilsTests
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtUtilsTests {

    @Test
    public void jwsClaimsSetTests() throws Exception {
        String uuid = UUID.randomUUID().toString();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().jwtID(uuid).subject(uuid).claim(JWT_KEY_UID, uuid).build();
        String token = JwtUtils.createJwsToken(claimsSet);
        Assertions.assertNotNull(token);

        JWTClaimsSet parsedClaimsSet = JwtUtils.parseJwsToken(token);
        Assertions.assertNotNull(parsedClaimsSet);
        Assertions.assertEquals(claimsSet.getSubject(), parsedClaimsSet.getSubject());
        Assertions.assertEquals(claimsSet.getJWTID(), parsedClaimsSet.getJWTID());
        Assertions.assertEquals(claimsSet.getStringClaim(JWT_KEY_UID), parsedClaimsSet.getClaim(JWT_KEY_UID));

        Map<String, Object> payloadMap = JwtUtils.verifyJwsToken(token);
        Assertions.assertNotNull(payloadMap);
        Assertions.assertEquals(MapUtils.getString(payloadMap, JWT_KEY_UID), uuid);
    }

    @Test
    public void jwsJSONObjectTests() throws Exception {
        String uuid = UUID.randomUUID().toString();

        Map<String, Object> map = new HashMap<>();
        map.putIfAbsent(JWT_KEY_UID, uuid);

        String token = JwtUtils.createJwsToken(map);
        Assertions.assertNotNull(token);

        Map<String, Object> payloadMap = JwtUtils.verifyJwsToken(token);
        Assertions.assertNotNull(payloadMap);
        Assertions.assertEquals(MapUtils.getString(payloadMap, JWT_KEY_UID), uuid);
    }

    @Test
    public void jwtTests() throws Exception {
        String uuid = UUID.randomUUID().toString();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().jwtID(uuid).subject(uuid).claim(JWT_KEY_UID, uuid).build();
        String token = JwtUtils.createJwtToken(claimsSet);
        Assertions.assertNotNull(token);

        JWTClaimsSet parsedClaimsSet = JwtUtils.verifyJwtToken(token);
        Assertions.assertNotNull(parsedClaimsSet);
        Assertions.assertEquals(claimsSet.getSubject(), parsedClaimsSet.getSubject());
        Assertions.assertEquals(claimsSet.getJWTID(), parsedClaimsSet.getJWTID());
        Assertions.assertEquals(claimsSet.getClaim(JWT_KEY_UID), parsedClaimsSet.getClaim(JWT_KEY_UID));
    }

    @Test
    public void generateSecretTests() throws Exception {
        String secret = JwtUtils.generateSecret(32);
        log.debug(secret);
        Assertions.assertNotNull(secret);
        Assertions.assertEquals(secret.length(), 32);
    }

}
