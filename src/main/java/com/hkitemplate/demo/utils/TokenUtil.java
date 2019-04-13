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

import java.security.MessageDigest;
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
    public static String createToken(String tel) throws Exception {

        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String secret = new Date().getTime() + "zwsj";//随机数

//        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date exDate = sdf.parse(sdf.format(date));
        Date expires = new Date(exDate.getTime() + 1000 * 60 * 60 * 24 * 7 + 1000 * 60 * 60 * 3);//这里设置的到期时间为生成JWT的一周后的凌晨3点
        String token = JWT.create()
                .withHeader(map)//header
                .withClaim("tel", tel)//自定义业务字段
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
