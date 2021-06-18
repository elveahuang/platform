/**
 *
 */
const PATTERN_PASSWORD = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;

export const isValidPassword = (password: string): boolean => {
    return PATTERN_PASSWORD.test(password);
};
