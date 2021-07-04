import { get, post } from '../utils/http';
import { Credentials } from '../types/credentials';

/**
 * 用户服务
 */
class AuthService {
    /**
     * 用户登录
     */
    auth = (credentials: Credentials) => {
        return post('/api/auth/token', credentials);
    };
    /**
     * 用户退出登录
     */
    logout = (accessToken: string) => {
        return get('/api/logout', {
            accessToken: accessToken,
        });
    };
    /**
     * 获取用户信息
     */
    user = () => {
        return get('/api/user');
    };
    /**
     * 刷新凭证
     */
    refresh = () => {
        return post('/api/refresh');
    };
}

export default new AuthService();
