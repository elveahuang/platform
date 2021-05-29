import { get, post, postJson } from '../utils/http';
import { Credentials } from '../types/credentials';

/**
 * 用户服务
 */
class UserService {
    /**
     * 用户登录
     */
    auth = (credentials: Credentials) => {
        return post('/auth/token', credentials);
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
    /**
     * 用户注册
     */
    register = (params = {}) => {
        return postJson('/api/user/register', params);
    };
    /**
     * 获取登录账号个人信息
     */
    profile = () => {
        return post('/api/user/profile');
    };
    /**
     * 获取用户列表
     */
    getUserList = (params = {}, config = {}) => {
        return post('/api/user/list', params, config);
    };
}

export default new UserService();
