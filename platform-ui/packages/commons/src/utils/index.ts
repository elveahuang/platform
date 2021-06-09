import { defaultLang, LangType } from '@commons/utils/i18n';

/**
 * 是否是开发环境
 */
export const isDev = process.env.NODE_ENV === 'development';

/**
 * 获取浏览器语言
 */
export const getBrowserLocale = (): LangType => {
    let language;
    switch (navigator.language.toLowerCase()) {
        case 'zh-tw':
            language = LangType.ZH_TW;
            break;
        case 'zh-hk':
            language = LangType.ZH_TW;
            break;
        case 'zh-cn':
            language = LangType.ZH_CN;
            break;
        default:
            language = defaultLang;
            break;
    }
    return language;
};
