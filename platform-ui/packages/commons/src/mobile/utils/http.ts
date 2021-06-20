import http, { isTimeoutError } from '@commons/utils/http';
import { AxiosResponse } from 'axios';
import { toast } from '@commons/mobile/utils/ionic';
import { StorageService, UserService } from '@commons/services';

let isRefreshing = false;
const requests: Array<(token: string) => void> = [];

const setupHttp = () => {
    http.interceptors.response.use(
        (response) => {
            if (response.status === 200 && response.data.code !== 1) {
                toast(response.data.message);
            }
            return response;
        },
        (error) => {
            if (error.response) {
                const response = error.response as AxiosResponse;
                switch (response.status) {
                    case 404:
                        toast('接口不存在');
                        break;
                    case 401:
                        const originalConfig = response.config;
                        if (isRefreshing) {
                            return new Promise((resolve) => {
                                requests.push((token: string) => {
                                    originalConfig.headers.Authorization = 'Bearer ' + token;
                                    resolve(http(originalConfig));
                                });
                            });
                        }
                        isRefreshing = true;
                        return UserService.refresh()
                            .then(() => {
                                const token = StorageService.getAccessToken();
                                originalConfig.headers.Authorization = 'Bearer ' + token;
                                requests.forEach((resolve) => resolve(token));
                                return http(originalConfig);
                            })
                            .catch(() => {
                                toast('凭证已过期');
                            })
                            .finally(() => {
                                isRefreshing = false;
                            });
                    case 503:
                        toast('请稍后再试');
                        break;
                    default:
                        break;
                }
            } else {
                if (isTimeoutError(error)) {
                    toast('网络连接超时');
                } else {
                    toast('网络好像出了点问题,请稍后再试');
                }
            }
            return Promise.reject(error);
        },
    );
};

export { setupHttp };
