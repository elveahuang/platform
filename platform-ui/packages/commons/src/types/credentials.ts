/**
 * 用户凭证
 */
export interface Credentials {
    /**
     * 用户名
     */
    readonly username: string;
    /**
     * 昵称
     */
    readonly password: string;
    /**
     * 客户端版本
     */
    readonly clientVersion?: string;
    /**
     * 登录平台
     */
    readonly platform?: string;
    /**
     * 客户端设备
     */
    readonly deviceModel?: string;
    /**
     * 客户端版本
     */
    readonly deviceVersion?: string;
    /**
     * Client ID
     */
    readonly client_id?: string;
    /**
     * Client Secret
     */
    readonly client_secret?: string;
    /**
     * Grant Type
     */
    readonly grant_type?: string;
}
