package cn.elvea.platform.ds.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 拦截强制主路由注解
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Aspect
public class MasterDataSourceAspect {

    @Pointcut("@annotation(cn.elvea.platform.base.annotations.MasterDataSource)")
    public void masterDataSourceAspect() {
    }

    @Before("masterDataSourceAspect()")
    public void doBefore() {
        changeMasterSlaveStrategy();
    }

    @After("masterDataSourceAspect()")
    public void doAfter() {
        HintManager.clear();
    }

    private void changeMasterSlaveStrategy() {
        HintManager hintManager = HintManager.getInstance();
        hintManager.setWriteRouteOnly();
    }

}
