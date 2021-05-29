package cn.elvea.platform.commons;

import cn.elvea.platform.commons.config.Config;
import cn.elvea.platform.commons.language.LanguageResolver;
import cn.elvea.platform.commons.user.User;
import cn.elvea.platform.commons.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;

/**
 * Context
 *
 * @author elvea
 * @since 0.0.1
 */
public class Context {

    private final Config config;

    private MessageSource messageSource;

    public Context(Config config) {
        this.config = config;
    }

    /**
     * 获取当前系统是否是调试模式
     */
    public boolean isDebug() {
        return this.config.isDebug();
    }

    /**
     * 获取当前用户
     */
    public User getCurrentUser() {
        return new User() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public String getUsername() {
                return "admin";
            }
        };
    }

    /**
     * 获取当前语言
     */
    public String getCurrentLanguage() {
        LanguageResolver resolver = SpringUtils.getBean(LanguageResolver.class);
        return resolver.resolveLanguage();
    }



    @Autowired
    @Lazy
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
