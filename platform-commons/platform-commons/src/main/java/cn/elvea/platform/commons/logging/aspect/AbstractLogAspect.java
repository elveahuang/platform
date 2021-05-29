package cn.elvea.platform.commons.logging.aspect;

import cn.elvea.platform.commons.logging.provider.LoggingProviderManager;
import cn.elvea.platform.commons.utils.JsonUtils;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * AbstractLogAspect
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractLogAspect {

    protected final LoggingProviderManager loggingProviderManager;

    public AbstractLogAspect(LoggingProviderManager loggingProviderManager) {
        this.loggingProviderManager = loggingProviderManager;
    }

    protected abstract void output(JoinPoint joinPoint, Long execTime, Throwable e);

    protected String getJoinPointClass(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }

    protected String getJoinPointMethod(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName() + "()";
    }

    protected String getRequestParams(HttpServletRequest request) throws Exception {
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder requestParams = new StringBuilder();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String value = request.getParameter(parameterName);
            requestParams.append(parameterName).append(" : ").append(JsonUtils.toJson(value)).append(";");
        }
        return requestParams.toString();
    }

    protected Map<String, String> getAnnotationValue(JoinPoint joinPoint) throws Exception {
        return Maps.newHashMap();
    }

}
