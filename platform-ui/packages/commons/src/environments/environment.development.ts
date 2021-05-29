import EnvironmentInterface from './environment.interface';

/**
 * 开发环境配置
 *
 * @type EnvironmentInterface
 */
const env: EnvironmentInterface = {
    /**
     * 环境名称
     */
    mode: 'development',
    /**
     * 是否生产模式
     */
    production: false,
    /**
     * 服务器地址
     */
    server: 'http://127.0.0.1:8080',
    /**
     * XDebug
     */
    xdebug: {
        enabled: false,
        key: 'XDebug',
    },
    /**
     * VConsole
     */
    console: {
        enabled: true,
    },
};

export { env };
