import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios';
import qs from 'qs';
import { isArray, isEmpty, merge } from 'lodash';
//
import StorageService from '../services/StorageService';
import environment from '../environments/environment';

/**
 * 设置全局参数
 */
axios.defaults.timeout = 300000;
axios.defaults.baseURL = environment.server;
axios.defaults.withCredentials = false;
axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded';
/**
 * XDebug
 */
if (environment.xdebug.enabled) {
    console.log(`xdebug enabled - ${environment.xdebug.enabled}`);
    axios.defaults.params = {
        XDEBUG_SESSION_START: 'XDebug',
    };
}
/**
 * 封装实例
 */
const http = axios.create({
    withCredentials: false,
});
/**
 * XDebug
 */
http.interceptors.request.use(async (config: AxiosRequestConfig) => {
    if (environment.xdebug.enabled) {
        if (config.params) {
            if (isArray(config.params)) {
                config.params = merge(config.params, {
                    XDEBUG_SESSION_START: 'XDebug',
                });
            }
        } else {
            config.params = {
                XDEBUG_SESSION_START: 'XDebug',
            };
        }
        console.log(`xdebug enabled - ${environment.xdebug.enabled}`);
        console.log(config);
    }
    return config;
});

/**
 * Authorization
 */
http.interceptors.request.use(async (config: AxiosRequestConfig) => {
    const token = await StorageService.getAccessToken();
    if (isEmpty(token)) {
        config.headers.common.Authorization = null;
    } else {
        config.headers.common.Authorization = `Bearer ${token}`;
    }
    console.log(`Authorization ${config.headers.common.Authorization}`);
    return config;
});

http.interceptors.response.use(
    async (response: AxiosResponse) => {
        console.log('1...........');
        console.log(response);
        return response;
    },
    async (error: AxiosError) => {
        console.log('2...........');
        const originalRequest = error.config;
        console.log(originalRequest);
        console.log(error);
        // const refreshToken : any = await StorageService.getRefreshToken();
        // // CommonService.refreshAccessToken(refreshToken).then((resp : AxiosResponse) => {
        // //     if (resp.status === 200 && resp.data.status === 1) {
        // //     }
        // // });
        return Promise.reject(error);
    },
);

/**
 * 文件上传的表单头信息
 */
const postMultipartHeaders: AxiosRequestConfig = {
    headers: {
        'Content-Type': 'multipart/form-data',
    },
    transformRequest: (data: any) => {
        return data;
    },
};

/**
 * 文件上传的表单头信息
 */
const postFormHeaders: AxiosRequestConfig = {
    headers: {
        'Content-Type': 'multipart/form-data',
    },
    transformRequest: (data: any) => {
        return data;
    },
};

/**
 * Json数据的表单头信息
 */
const postJsonHeaders: AxiosRequestConfig = {
    headers: {
        'Content-Type': 'application/json',
    },
    transformRequest: (data: any) => {
        return JSON.stringify(data);
    },
};

/**
 * 表单头信息
 */
const postHeaders: AxiosRequestConfig = {
    transformRequest: (data: any, headers: any) => {
        console.log('transformRequest...');
        console.log(headers);
        console.log(qs.stringify(data));
        return qs.stringify(data);
    },
    paramsSerializer: (data: any = {}) => {
        console.log('paramsSerializer...');
        console.log(data);
        return qs.stringify(data);
    },
};

// Get
function get<T = any, R = AxiosResponse<T>>(
    url: string,
    params: any = {},
    config: AxiosRequestConfig = {},
): Promise<R> {
    config.params = params;
    return http.get(url, config);
}

// Post
function post<T = any, R = AxiosResponse<T>>(
    url: string,
    data: any = {},
    config: AxiosRequestConfig = postHeaders,
): Promise<R> {
    return http.post(url, data, config);
}

// Post Json
function postJson<T = any, R = AxiosResponse<T>>(
    url: string,
    data: any = {},
    config: AxiosRequestConfig = postJsonHeaders,
): Promise<R> {
    return http.post(url, data, config);
}

// Post FormBody
function postForm<T = any, R = AxiosResponse<T>>(
    url: string,
    data: FormData = new FormData(),
    config: AxiosRequestConfig = postFormHeaders,
): Promise<R> {
    return http.post(url, data, config);
}

// Post Multiple
function postMultiple<T = any, R = AxiosResponse<T>>(
    url: string,
    data: any = {},
    config: AxiosRequestConfig = postMultipartHeaders,
): Promise<R> {
    return http.post(url, data, config);
}

export default http;
export { axios, get, post, postForm, postJson, postMultiple };
export { postHeaders, postFormHeaders, postJsonHeaders, postMultipartHeaders };
