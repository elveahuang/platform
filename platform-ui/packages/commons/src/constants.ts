import zhCnIntl from './locales/zh_CN';
import zhTwIntl from './locales/zh_TW';
import enUsIntl from './locales/en_US';

/**
 * 版本号
 */
export const applicationVersion = '0.0.1';

/**
 * 可用语言
 */
export enum availableApplicationLocales {
    ZH_CN = 'zh-CN',
    ZH_TW = 'zh-TW',
    EN_US = 'en-US',
}

/**
 * 默认语言
 */
export const defaultApplicationLocale = availableApplicationLocales.EN_US;

/**
 * react-intl
 */
export const applicationLocaleMessages = {
    'zh-CN': zhCnIntl,
    'zh-TW': zhTwIntl,
    'en-US': enUsIntl,
};

/**
 * 获取浏览器语言
 */
export const getBrowserLocale = (): availableApplicationLocales => {
    let language;
    switch (navigator.language.toLowerCase()) {
        case 'zh-tw':
            language = availableApplicationLocales.ZH_TW;
            break;
        case 'zh-hk':
            language = availableApplicationLocales.ZH_TW;
            break;
        case 'zh-cn':
            language = availableApplicationLocales.ZH_CN;
            break;
        default:
            language = availableApplicationLocales.EN_US;
            break;
    }
    return language;
};

/**
 * 默认分页每页记录数
 */
export const defaultPageSize = 12;
/**
 * 默认显示第几页
 */
export const defaultPage = 1;
