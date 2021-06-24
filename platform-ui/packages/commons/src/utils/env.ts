import { isEqual } from 'lodash';

export interface Environment {
    /**
     * 环境名称
     */
    mode: string;
    /**
     * 是否是生产模式
     */
    production: boolean;
    /**
     * 应用名称
     */
    title: string;
    /**
     * 服务器地址
     */
    server: string;
    /**
     * XDebug
     */
    xdebug: {
        enabled: boolean;
        key: string;
    };
    /**
     * VConsole
     */
    console: {
        enabled: boolean;
    };
}

/**
 * 环境配置
 */
const env: Environment = {
    mode: import.meta.env.MODE ?? 'development',
    production: isEqual(import.meta.env.NODE_HOME, 'production'),
    title: import.meta.env.VITE_APP_TITLE ?? 'Application',
    server: import.meta.env.VITE_APP_SERVER ?? '',
    xdebug: {
        enabled: !!import.meta.env.VITE_APP_XDEBUG_ENABLED,
        key: import.meta.env.VITE_APP_XDEBUG_KEY ?? 'XDebug',
    },
    console: {
        enabled: !!import.meta.env.VITE_APP_CONSOLE_ENABLED,
    },
};

export default env;
