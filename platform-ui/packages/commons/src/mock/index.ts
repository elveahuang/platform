import Mock from 'mockjs';

// @ts-ignore
Mock.XHR.prototype.__send = Mock.XHR.prototype.send;
// @ts-ignore
Mock.XHR.prototype.send = function () {
    if (this.custom.xhr) {
        this.custom.xhr.withCredentials = this.withCredentials || false;
        this.custom.xhr.responseType = this.responseType;
    }
    this.__send.apply(this, arguments);
};

const tokens = {
    admin: 'admin-token',
    guest: 'guest-token',
};

Mock.mock(/\/initialize/, 'get', () => {
    return {};
});

Mock.mock(/\/auth\/token/, 'post', () => {
    const username = 'admin';
    const token = tokens[username as keyof typeof tokens];
    if (!token) {
        return {
            code: 0,
            message: '用户名或密码错误',
        };
    }
    return {
        access_token: token,
        refresh_token: 'refreshToken',
    };
});

Mock.mock(/\/auth\/user\/userInfo/, 'get', () => {
    return {
        code: 1,
        data: {
            username: 'ggboy',
            id: 111,
            tenantId: '1',
        },
    };
});

export default Mock;
