/**
 * 用户凭证
 */
export interface Principal {
    /**
     * 唯一标识
     */
    readonly uid?: number;
    /**
     * 用户名
     */
    readonly username?: string;
    /**
     * 昵称
     */
    readonly nickname?: string;
    /**
     * 用户角色
     */
    readonly roles?: string[];
    /**
     * 用户权限
     */
    readonly authorities?: string[];
}
