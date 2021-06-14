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
    GREEN,
    BLUE,
}

/**
 * 默认主题
 */
export const defaultTheme = ThemeType.BLUE;

/**
 * 内置主题
 */
export const themes = [
    {
        key: ThemeType.GREEN,
        primaryColor: 'red',
        primarySecondary: 'green',
    },
    {
        key: ThemeType.BLUE,
        primaryColor: 'blue',
        primarySecondary: 'yellow',
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
