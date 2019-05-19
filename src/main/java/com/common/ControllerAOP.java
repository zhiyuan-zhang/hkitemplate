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
public class ControllerAOP {
	private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);
	
	
	//@Pointcut("execution(* com.hkitemplate.demo.controller.*.*(..))")
	@Pointcut("execution(public com.hkitemplate.demo.beans.ResultBean *(..))")
    public void webLog(){}
 
    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录请求内容
        logger.info("对象请求的URL : " + request.getRequestURL().toString());
        logger.info("请求方法名称 : " + request.getMethod());
        logger.info("对方IP地址 : " + request.getRemoteAddr());
        logger.info("运行的java类 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));

    }
 
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
    	logger.info("方法的返回值 : " + ret);
    }
 
    //后置异常通知
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp){
    	logger.info("方法异常时执行.....");
    }
  //后置异常通知
    @AfterThrowing(throwing = "ex", pointcut = "webLog()")
    public void throwss(JoinPoint jp, Exception ex){
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint jp){
    	logger.info("方法最后执行.....");
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
    	logger.info("@Around:进入服务器端开始记录日志 .....");

        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();
            logger.info("@Around:结果是 :" + pjp.getSignature() + " use time: " + (System.currentTimeMillis() - startTime));
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
			logger.error(pjp.getSignature() + " error ", e);

			// TODO 未知的异常，应该格外注意，可以发送邮件通知等
			result.setMsg(e.toString());
			result.setCode(ResultBean.UNKNOWN_EXCEPTION);
		}

		return result;
	}
}


