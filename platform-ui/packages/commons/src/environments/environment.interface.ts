interface EnvironmentInterface {
    /**
     * 环境名称
     */
    mode: string;
    /**
     * 是否是生产模式
     */
    production: boolean;
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

export default EnvironmentInterface;
