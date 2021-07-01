package cn.elvea.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * SpringUtils
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "applicationContext must not be null.");
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * ApplicationContext
     *
     * @return {@link ApplicationContext}
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取实例
     *
     * @param name 名称
     * @return Object
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 获取实例
     *
     * @param name         名称
     * @param requiredType 类型
     * @param <T>          T
     * @return T
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 获取实例
     *
     * @param clazz T
     * @param <T>   T
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 检查是否包含该类
     *
     * @param name String
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 检查是否是单例
     *
     * @param name String
     * @return boolean
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取类型
     *
     * @param name String
     * @return Class<?>
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 动态注册单例对象
     *
     * @param beanName        对象名称
     * @param singletonObject 对象实例
     */
    public static void registerSingleton(String beanName, Object singletonObject) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) SpringUtils.applicationContext.getAutowireCapableBeanFactory();
        if (!beanFactory.containsSingleton(beanName)) {
            beanFactory.registerSingleton(beanName, singletonObject);
        } else {
            log.error("singleton bean with name [{}] allready exists.", beanName);
        }
    }

    /**
     * 动态注册单例对象
     *
     * @param beanName       对象名称
     * @param beanDefinition 对象定义
     */
    public static void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

    /**
     * 获取HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest();
    }

    /**
     * 获取HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getResponse();
    }

    /**
     * 获取HttpSession
     */
    public static HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest().getSession();
    }

    public static ApplicationContext publishEvent(Object event) {
        applicationContext.publishEvent(event);
        return applicationContext;
    }

}
