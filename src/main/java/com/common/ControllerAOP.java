/**  
* <p>Title: ControllerAOP.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: zhongwangkeji</p>  
* @author zhanghaow
* @date 2018年9月12日  
* @version 1.0  
*/  
package com.common;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.exceptions.CheckException;
import com.common.exceptions.NoPermissionException;
import com.common.exceptions.UnloginException;
import com.hkitemplate.demo.beans.ResultBean;


/**
 * @author zhanghaow
 *<p>Title: ControllerAOP</p> 
 *
 *<p>Description: </p> 
 *
 * 2018年3月12日 下午12:01:56
 */
@Aspect
@Component
@Order(-99)
@Slf4j
public class ControllerAOP {
//	private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);
	
	
	//@Pointcut("execution(* com.hkitemplate.demo.controller.*.*(..))")
	@Pointcut("execution(public com.hkitemplate.demo.beans.ResultBean *(..))")
    public void webLog(){}
 
    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null && joinPoint != null) {
            HttpServletRequest request = attributes.getRequest();
            MDC.put("THREAD_ID", ""+request.hashCode());
            // 记录请求内容
            log.info( "1. 对象请求的URL : {}", request.getRequestURL().toString());
            log.info( "2. 请求方法名称 : {}" , request.getMethod());
            log.info( "3. 对方IP地址 : {}" , request.getRemoteAddr());
            log.info( "4. 运行的java类 : {}" , joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            try{
                log.info( "5. 请求参数 :{} ", JSONObject.toJSONString(joinPoint.getArgs() ));
            }catch (Exception e){
                log.info("请求参数切点无法切入");
            }

        }else{
            throw new CheckException("网络请求出错, 请清空缓存重新尝试. ");
        }

    }
 
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        log.info("{} : 方法的返回值 : {}", request.hashCode(),  ret);
    }
 
    //后置异常通知
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp){
        log.info("方法异常时执行.....");
    }
  //后置异常通知
    @AfterThrowing(throwing = "ex", pointcut = "webLog()")
    public void throwss(JoinPoint jp, Exception ex){
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp){
        log.info("方法最后执行.....");
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        log.info("@Around:进入服务器端开始记录日志 .....");

        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();
            log.info("@Around:结果是 :{} use time: {}", pjp.getSignature(),  (System.currentTimeMillis() - startTime ));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }
    

	private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
		ResultBean<?> result = new ResultBean();

		// 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
		// 校验出错，参数非法
		if (e instanceof CheckException || e instanceof IllegalArgumentException) {
			result.setMsg(e.getLocalizedMessage());
			result.setCode(ResultBean.CHECK_FAIL);
		}
		// 没有登陆
		else if (e instanceof UnloginException) {
			result.setMsg("Unlogin");
			result.setCode(ResultBean.NO_LOGIN);
		}
		// 没有权限
		else if (e instanceof NoPermissionException) {
			result.setMsg("NO PERMISSION");
			result.setCode(ResultBean.NO_PERMISSION);
		} else {
            log.error(" error {}", e);

			// TODO 未知的异常，应该格外注意，可以发送邮件通知等
			result.setMsg(e.toString());
			result.setCode(ResultBean.UNKNOWN_EXCEPTION);
		}

		return result;
	}
}


