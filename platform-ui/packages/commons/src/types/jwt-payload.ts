import Principal from './principal';

/**
 * JwtPayload
 */
export interface JwtPayload {
    /**
     * 会话ID
     */
    readonly id: string;
    /**
     * 用户信息
     */
    readonly principal: Principal;
}
