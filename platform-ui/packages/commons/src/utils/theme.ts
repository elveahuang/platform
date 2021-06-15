/**
 * 主题类型
 */
export type Theme = {
    primaryColor: string;
    primarySecondary: string;
};

/**
 * 主题枚举
 */
export enum ThemeType {
    DEFAULT,
    GREEN,
    BLUE,
}

/**
 * 对齐方式枚举
 */
export enum DirectionType {
    LTR = 'ltr',
    RTL = 'rtl',
}

/**
 * 默认主题
 */
export const defaultTheme = ThemeType.DEFAULT;

/**
 * 内置主题
 */
export const themes = [
    {
        key: ThemeType.DEFAULT,
        primaryColor: 'green',
        primarySecondary: 'green',
    },
    {
        key: ThemeType.GREEN,
        primaryColor: 'red',
        primarySecondary: 'red',
    },
    {
        key: ThemeType.BLUE,
        primaryColor: 'blue',
        primarySecondary: 'blue',
    },
];

/**
 * 用于实时设置浏览器主题
 */
export const setTheme = (theme: ThemeType = defaultTheme) => {
    const t = themes.find((element) => element.key === theme);
    document.body.style.setProperty('--color-primary', t.primaryColor);
    document.body.style.setProperty('--color-secondary', t.primarySecondary);
};

export default themes;
