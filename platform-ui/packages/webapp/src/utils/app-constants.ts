import zhCnProvider from 'antd/lib/locale-provider/zh_CN';
import zhTwProvider from 'antd/lib/locale-provider/zh_TW';
import enUsProvider from 'antd/lib/locale-provider/en_US';

/**
 * antd-config-prodiver
 */
export const applicationLocalProdiver = {
    'zh-CN': zhCnProvider,
    'zh-TW': zhTwProvider,
    'en-US': enUsProvider,
};
/**
 * 水平表单样式
 */
export const detailField = {
    style: {
        marginBottom: 10,
    },
};

export const detailFieldLabel = {
    xs: { span: 4 },
    sm: { span: 4 },
    md: { span: 4 },
    lg: { span: 4 },
    xl: { span: 4 },
    xxl: { span: 4 },
};

export const detailFieldValue = {
    xs: { span: 18 },
    sm: { span: 18 },
    md: { span: 18 },
    lg: { span: 18 },
    xl: { span: 18 },
    xxl: { span: 18 },
};

export const detailTail = {
    xs: { span: 18, offset: 4 },
    sm: { span: 18, offset: 4 },
    md: { span: 18, offset: 4 },
    lg: { span: 18, offset: 4 },
    xl: { span: 18, offset: 4 },
    xxl: { span: 18, offset: 4 },
};

/**
 * 水平表单布局样式
 */
export const formItemLayout = {
    labelCol: {
        xs: { span: 24 },
        sm: { span: 4 },
    },
    wrapperCol: {
        xs: { span: 24 },
        sm: { span: 12 },
    },
};

export const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 12,
            offset: 4,
        },
    },
};
