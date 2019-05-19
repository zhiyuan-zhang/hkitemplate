package com.hkitemplate.demo.filter;


import com.common.exceptions.TokenErrorException;
import com.hkitemplate.demo.utils.TokenUtil;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class ParamInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //request.getHeader(String) 从请求头中获取数据
        //从请求头中获取用户token（登陆凭证根据业务而定）
        log.info("url is : " + request.toString());
        String userId = vtoken(request.getHeader("X-Token"));
        if (userId == null) {
            throw new TokenErrorException("token ERROR");
        }
        request.setAttribute("tel", userId);
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
