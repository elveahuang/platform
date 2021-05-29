import { isEmpty } from 'lodash';

/**
 * Key
 */
const ACCESS_TOKEN_KEY = 'ACCESS_TOKEN';

const REFRESH_TOKEN_KEY = 'REFRESH_TOKEN';

/**
 * 存储服务
 */
class StorageService {
    getAccessToken = (): string => {
        return localStorage.getItem(ACCESS_TOKEN_KEY);
    };

    removeAccessToken = (): void => {
        localStorage.removeItem(ACCESS_TOKEN_KEY);
    };

    setAccessToken = (val: string): void => {
        if (isEmpty(val)) {
            this.removeAccessToken();
        } else {
            localStorage.setItem(ACCESS_TOKEN_KEY, val);
        }
    };

    getRefreshToken = (): string => {
        return localStorage.getItem(REFRESH_TOKEN_KEY);
    };

    removeRefreshToken = (): void => {
        localStorage.removeItem(REFRESH_TOKEN_KEY);
    };

    setRefreshToken = (val: string): void => {
        if (isEmpty(val)) {
            this.removeRefreshToken();
        } else {
            localStorage.setItem(REFRESH_TOKEN_KEY, val);
        }
    };

    getItem = (key: string): string => {
        return localStorage.getItem(key);
    };

    removeItem = (key: string): void => {
        localStorage.removeItem(key);
    };

    setItem = (key: string, val: string): void => {
        if (val) {
            localStorage.setItem(key, val);
        } else {
            localStorage.removeItem(key);
        }
    };

    clear = () => {
        return localStorage.clear();
    };
}

const storageService = new StorageService();

export default storageService;
