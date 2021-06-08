import { isEmpty } from 'lodash';

const ACCESS_TOKEN_KEY = 'ACCESS_TOKEN';

const REFRESH_TOKEN_KEY = 'REFRESH_TOKEN';

const StorageService = {
    /**
     *
     */
    getItem(key: string): string {
        return localStorage.getItem(key) ?? '';
    },
    /**
     *
     */
    removeItem(key: string): void {
        localStorage.removeItem(key);
    },
    /**
     *
     */
    setItem(key: string, val: string): void {
        if (!isEmpty(val)) {
            localStorage.setItem(key, val);
        } else {
            this.removeItem(key);
        }
    },
    /**
     *
     */
    getAccessToken(): string {
        return this.getItem(ACCESS_TOKEN_KEY) ?? '';
    },
    /**
     *
     */
    setAccessToken(val: string): void {
        this.setItem(ACCESS_TOKEN_KEY, val);
    },
    /**
     *
     */
    removeAccessToken(): void {
        this.removeItem(ACCESS_TOKEN_KEY);
    },
    /**
     *
     */
    getRefreshToken(): string {
        return this.getItem(REFRESH_TOKEN_KEY) ?? '';
    },
    /**
     *
     */
    setRefreshToken(val: string): void {
        this.setItem(REFRESH_TOKEN_KEY, val);
    },
    /**
     *
     */
    removeRefreshToken(): void {
        this.removeItem(REFRESH_TOKEN_KEY);
    },
    /**
     *
     */
    clear() {
        localStorage.clear();
    },
};

export default StorageService;
