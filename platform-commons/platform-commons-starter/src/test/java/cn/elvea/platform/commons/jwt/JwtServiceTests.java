package cn.elvea.platform.commons.jwt;

import cn.elvea.platform.commons.CommonsTestApplicationTests;
import cn.elvea.platform.commons.utils.UuidUtils;
import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static cn.elvea.platform.commons.utils.JwtUtils.JWT_KEY_UID;

/**
 * JwtServiceTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtServiceTests extends CommonsTestApplicationTests {

    @Autowired
    JwtService jwtService;

    @Test
    public void jwsClaimsSetTests() throws Exception {
        String uuid = UuidUtils.uuid();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().jwtID(uuid).subject(uuid).claim(JWT_KEY_UID, uuid).build();
        String token = jwtService.createJwsToken(claimsSet);
        Assertions.assertNotNull(token);

        JWTClaimsSet parsedClaimsSet = jwtService.parseJwsToken(token);
        Assertions.assertNotNull(parsedClaimsSet);
        Assertions.assertEquals(claimsSet.getSubject(), parsedClaimsSet.getSubject());
        Assertions.assertEquals(claimsSet.getJWTID(), parsedClaimsSet.getJWTID());
        Assertions.assertEquals(claimsSet.getStringClaim(JWT_KEY_UID), parsedClaimsSet.getClaim(JWT_KEY_UID));

        Map<String, Object> payloadMap = jwtService.verifyJwsToken(token);
        Assertions.assertNotNull(payloadMap);
        Assertions.assertEquals(MapUtils.getString(payloadMap, JWT_KEY_UID), uuid);
    }

    @Test
    public void jwsJSONObjectTests() throws Exception {
        String uuid = UuidUtils.uuid();

        Map<String, Object> map = new HashMap<>();
        map.putIfAbsent(JWT_KEY_UID, uuid);

        String token = jwtService.createJwsToken(map);
        Assertions.assertNotNull(token);

        Map<String, Object> payloadMap = jwtService.verifyJwsToken(token);
        Assertions.assertNotNull(payloadMap);
        Assertions.assertEquals(MapUtils.getString(payloadMap, JWT_KEY_UID), uuid);
    }

    @Test
    public void jwtTests() throws Exception {
        String uuid = UuidUtils.uuid();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().jwtID(uuid).subject(uuid).claim(JWT_KEY_UID, uuid).build();
        String token = jwtService.createJwtToken(claimsSet);
        Assertions.assertNotNull(token);

        JWTClaimsSet parsedClaimsSet = jwtService.verifyJwtToken(token);
        Assertions.assertNotNull(parsedClaimsSet);
        Assertions.assertEquals(claimsSet.getSubject(), parsedClaimsSet.getSubject());
        Assertions.assertEquals(claimsSet.getJWTID(), parsedClaimsSet.getJWTID());
        Assertions.assertEquals(claimsSet.getClaim(JWT_KEY_UID), parsedClaimsSet.getClaim(JWT_KEY_UID));
    }

}
