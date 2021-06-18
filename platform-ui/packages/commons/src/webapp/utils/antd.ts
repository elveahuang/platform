import zhCnProvider from 'antd/lib/locale-provider/zh_CN';
import zhTwProvider from 'antd/lib/locale-provider/zh_TW';
import enUsProvider from 'antd/lib/locale-provider/en_US';
import { LangType } from '@commons/utils/i18n';
import { message } from 'antd';

export const antdLocalProvider = {
    [LangType.ZH_CN]: zhCnProvider,
    [LangType.ZH_TW]: zhTwProvider,
    [LangType.EN_US]: enUsProvider,
};

export const toast = (msg: string) => {
    message.error(msg).then();
};
