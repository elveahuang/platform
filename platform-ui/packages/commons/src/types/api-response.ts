export default class ApiResponse<T = any> {
    static readonly CODE_SUCCESS: number = 1;
    static readonly CODE_ERROR: number = 0;
    static readonly CODE_TOKEN_EXPIRE: number = -1;
    static readonly CODE_CRASH: number = -2;
    static readonly CODE_ACCOUNT_EXPIRE: number = -3;
    static readonly CODE_ACCOUNT_LOCKED: number = -4;
    /**
     * 编号
     */
    code!: number;
    /**
     * 消息
     */
    message!: string;
    /**
     * 响应内容
     */
    data!: T;
}
