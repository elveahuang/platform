import { availableApplicationLocales, defaultApplicationLocale } from '../constants';

/**
 * 用户喜好
 */
export interface Preference {
    /**
     * 当前所在地区
     */
    locale: availableApplicationLocales;
    /**
     * 当前主题
     */
    theme: 'dark' | 'light';
    /**
     * 侧边栏是否收起
     */
    sidebarCollapsed: boolean;
}

/**
 * 默认用户喜好
 */
export const defaultPreference: Preference = {
    /**
     * 当前所在地区
     */
    locale: defaultApplicationLocale,
    /**
     * 当前主题
     */
    theme: 'light',
    /**
     * 侧边栏是否收起
     */
    sidebarCollapsed: false,
};
