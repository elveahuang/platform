package cn.elvea.platform.commons.logging.aspect;

import cn.elvea.platform.commons.logging.dto.OptLogDto;
import cn.elvea.platform.commons.logging.provider.LoggingProviderManager;
import cn.elvea.platform.commons.utils.ExceptionUtils;
import cn.elvea.platform.commons.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * OptLogAspect
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Aspect
public class OptLogAspect extends AbstractLogAspect {

    public OptLogAspect(LoggingProviderManager loggingProviderManager) {
        super(loggingProviderManager);
    }

    @Pointcut("@annotation(cn.elvea.platform.base.annotations.OptLog)")
    protected void optLogAspect() {
    }

    @Around("optLogAspect()")
    protected Object doAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        output(joinPoint, endTime - startTime, null);
        return result;
    }

    @AfterThrowing(pointcut = "optLogAspect()", throwing = "e")
    protected void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        output(joinPoint, 0L, e);
    }

    @Override
    protected void output(JoinPoint joinPoint, Long execTime, Throwable e) {
        try {
            HttpServletRequest request = ServletUtils.getRequest();
            OptLogDto dto = OptLogDto.builder()
                    .className(getJoinPointClass(joinPoint))
                    .methodName(getJoinPointMethod(joinPoint))
                    .requestIp(ServletUtils.getHost())
                    .requestUri(request.getRequestURI())
                    .requestParams(getRequestParams(request))
                    .httpMethod(request.getMethod())
                    .startTime(LocalDateTime.now())
                    .execTime(execTime)
                    .exception(e != null ? ExceptionUtils.getStackTraceAsString(e) : "")
                    .build();
            loggingProviderManager.saveLog(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
