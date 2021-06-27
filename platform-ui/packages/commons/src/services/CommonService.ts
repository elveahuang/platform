import { axios, get } from '../utils/http';

/**
 * 公共服务
 */
class CommonService {
    /**
     * 首页
     */
    initialize = () => {
        return get('/api/initialize');
    };
    /**
     * 首页
     */
    home = () => {
        return get('/api/home');
    };
    /**
     * Refresh Access Token
     */
    refreshAccessToken = (refreshToken: string) => {
        return axios.get('/api/auth/refresh', {
            params: { refreshToken: refreshToken },
        });
    };
}

export default new CommonService();
