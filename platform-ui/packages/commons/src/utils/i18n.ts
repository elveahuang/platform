import zhCnIntl from '../locales/zh_CN';
import zhTwIntl from '../locales/zh_TW';
import enUsIntl from '../locales/en_US';

/**
 *
 */
export enum LangType {
    ZH_CN = 'zh-CN',
    ZH_TW = 'zh-TW',
    EN_US = 'en-US',
}

/**
 * react-intl
 */
export const applicationLocaleMessages = {
    [LangType.ZH_CN]: zhCnIntl,
    [LangType.ZH_TW]: zhTwIntl,
    [LangType.EN_US]: enUsIntl,
};

/**
 * 默认语言
 */
export const defaultLang = LangType.ZH_CN;
