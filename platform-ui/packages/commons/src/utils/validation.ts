const PATTERN_PASSWORD = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;

/**
 * 密码规则，比如是包含大写字母，小写字母，数字和特殊字符
 */
export const isValidPassword = (password: string): boolean => {
    return PATTERN_PASSWORD.test(password);
};
