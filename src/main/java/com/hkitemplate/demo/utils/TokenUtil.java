package com.hkitemplate.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.common.exceptions.TokenErrorException;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 功能描述:  获取token 以及校验
 *
 * @return:
 * @auther: ZHANG.HAO
 * @date: 2019-02-19 17:36
 */
public class TokenUtil {

    private static final String SECRET = "template";

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public static String md5(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("md5");

            byte[] output = md.digest(password.getBytes());

            String pwd = Base64.encodeBase64String(output);

            return pwd;
        } catch (Exception e) {

            return "";
        }

    }

    /**
     * 通过登录名称密码id生成token
     *
     * @return
     * @throws Exception
     */
    public static String createToken(String arg) throws UnsupportedEncodingException, ParseException {

        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        //随机数
        String secret = System.currentTimeMillis() + "zhxt";

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date exDate = sdf.parse(sdf.format(date));
        Date expires = new Date(exDate.getTime() + 1000 * 60 * 60 * 24 * 7 + 1000 * 60 * 60 * 3);//这里设置的到期时间为生成JWT的一周后的凌晨3点
        String token = JWT.create()
                .withHeader(map)//header
                .withClaim("key", arg)//自定义业务字段
                .withClaim("now", secret)//加盐(随机数)
                .withExpiresAt(expires)//设置到期时间
                .sign(algorithm);
        return token;
    }

    public static String verifyToken(String token) throws Exception {

        String tel = "";
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();

            tel = claims.get("tel").asString();
        } catch (SignatureVerificationException e) {
            throw new TokenErrorException();
        } catch (JWTDecodeException e) {
            throw new TokenErrorException();
        }

        return tel;
    }

}
