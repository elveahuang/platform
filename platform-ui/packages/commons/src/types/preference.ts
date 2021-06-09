import { LangType } from '@commons/utils/i18n';

/**
 * 用户喜好
 */
export default interface Preference {
    /**
     * 当前所在地区
     */
    locale: LangType;
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
export const initialPreference = {
    /**
     * 当前所在地区
     */
    locale: LangType.ZH_CN,
    /**
     * 当前主题
     */
    theme: 'light',
    /**
     * 侧边栏是否收起
     */
    sidebarCollapsed: false,
} as Preference;
