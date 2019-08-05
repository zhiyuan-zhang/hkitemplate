package com.hkitemplate.demo.filter;


import com.common.exceptions.CheckException;
import com.common.exceptions.TokenErrorException;
import com.hkitemplate.demo.config.AccessLimit;
import com.hkitemplate.demo.utils.RedisUtil;
import com.hkitemplate.demo.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述: 拦截器用来拦截参数以及token
 *
 * @auther: ZHANG.HAO
 * @date: 2018/11/27 11:32 AM
 */
@Slf4j
public class ParamInterceptor implements HandlerInterceptor {


    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            boolean verifyToken = true;
            int max = 5;
            int time = 30;
            //组装key
            StringBuffer requestURL = request.getRequestURL();
            String queryString = request.getQueryString();
            String remoteAddr = request.getRemoteAddr();
            String headerToken = request.getHeader("X-Token");
            String userId = vtoken(headerToken);
            String key = requestURL + queryString + remoteAddr + userId;

//            log.error("key {}",key.hashCode());

            HandlerMethod hm = (HandlerMethod) handler;

            AccessLimit methodAnnotation = hm.getMethodAnnotation(AccessLimit.class);

            //查看是否有注解标识
            if (!ObjectUtils.isEmpty(methodAnnotation)) {
                //如果有注解 获取注解内容
                max = methodAnnotation.max();
                time = methodAnnotation.time();
                verifyToken = methodAnnotation.verifyToken();
            }

            //验证redis
            if (redisUtil.hasKey(key)) {
                double count = redisUtil.hincr("AccessLimit", key, 1);
                if (count < max) {
                    log.error("已经存在 key: {},count {}", key, count);
//                    redisUtil.hincr("AccessLimit", key, 1);

                } else {
//                    redisUtil.hdel("AccessLimit",key);
                    log.error("count: {}", count);
                    throw new CheckException("请求频繁!");
                }


            } else {

                synchronized (this) {
                    if (redisUtil.hasKey(key)) {
                        double count = redisUtil.hincr("AccessLimit", key, 1);
                        if (count < max) {
                            log.error("synchronized 已经存在 key: {},count {}", key, count);
//                            redisUtil.hincr("AccessLimit", key, 1);

                        } else {
//                            redisUtil.hdel("AccessLimit",key);
                            log.error("synchronized count: {}", count);
                            throw new CheckException("请求频繁!");
                        }


                    } else {
                        log.error("第一次 key: {},time {}", key , time);
                        redisUtil.set(key, "AccessLimit", time);
                        redisUtil.hdel("AccessLimit",key);
//                        redisUtil.hincr("AccessLimit",key, 1);

                    }

                }
            }

            //验证token
            if (verifyToken) {
//                log.error("url is : " + request.toString());

                if (ObjectUtils.isEmpty(userId)) {
                    throw new TokenErrorException();
                }
                String vtoken = (String)redisUtil.get(userId);
                if(!headerToken.equals(vtoken)){
                    throw new TokenErrorException("token失效，请重新登陆");
                }
                request.setAttribute("key", userId);

            }

            return true;


        }
        return true;


    }

    /**
     * 功能描述:
     *
     * @param token
     * @param:
     * @return:
     * @auther: ZHANG.HAO
     * @date: 2018/11/27 11:33 AM
     */
    public String vtoken(String token) {
        String verifyToken;
        try {
            verifyToken = TokenUtil.verifyToken(token);
        } catch (Exception e) {
            return null;
        }
        return verifyToken;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
