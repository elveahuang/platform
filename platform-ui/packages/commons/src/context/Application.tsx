import React, { createContext, useContext, useReducer } from 'react';
import { isEmpty } from 'lodash';
import Principal from '@commons/types/principal';
import Preference, { initialPreference } from '@commons/types/preference';
import storageService from '@commons/services/StorageService';
import { getBrowserLocale } from '@commons/utils';
import UserService from '@commons/services/UserService';
import { AxiosResponse } from 'axios';
import { LangType } from '@commons/utils/i18n';

export interface ApplicationState {
    /**
     * 是否已经登录认证
     */
    authenticated: boolean;
    /**
     * 访问凭证
     */
    accessToken: string;
    /**
     * 刷新凭证
     */
    refreshToken: string;
    /**
     * 用户凭证
     */
    principal: Principal;
    /**
     * 喜好
     */
    preference: Preference;
}

export const initialApplicationState: ApplicationState = {
    authenticated: false,
    accessToken: '',
    refreshToken: '',
    principal: null,
    preference: initialPreference,
};

export enum ApplicationContextActionType {
    INITIALIZE,
    LOGIN,
    LOGOUT,
    PRINCIPAL,
    TOGGLE_SIDER,
    TOGGLE_THEME,
    CHANGE_LOCALE,
}

export type ApplicationContextInitializeAction = {
    type: ApplicationContextActionType.INITIALIZE;
};

export type ApplicationContextLoginAction = {
    type: ApplicationContextActionType.LOGIN;
    access_token: string;
    refresh_token: string;
};

export type ApplicationContextLogoutAction = {
    type: ApplicationContextActionType.LOGOUT;
};

export type ApplicationContextSetPrincipalAction = {
    type: ApplicationContextActionType.PRINCIPAL;
    principal: Principal;
};

export type ApplicationContextToggleThemeAction = {
    type: ApplicationContextActionType.TOGGLE_THEME;
};

export type ApplicationContextToggleSiderAction = {
    type: ApplicationContextActionType.TOGGLE_SIDER;
};

export type ApplicationContextChangeLocaleAction = {
    type: ApplicationContextActionType.CHANGE_LOCALE;
    locale: LangType;
};

export type ApplicationContextAction =
    | ApplicationContextInitializeAction
    | ApplicationContextLoginAction
    | ApplicationContextLogoutAction
    | ApplicationContextSetPrincipalAction
    | ApplicationContextToggleThemeAction
    | ApplicationContextToggleSiderAction
    | ApplicationContextChangeLocaleAction;

export const reducer = (state: ApplicationState, action: ApplicationContextAction): ApplicationState => {
    switch (action.type) {
        case ApplicationContextActionType.LOGIN: // 用户成功登陆
            state.authenticated = true;
            state.accessToken = action.access_token;
            state.refreshToken = action.refresh_token;
            //
            storageService.setAccessToken(action.access_token);
            storageService.setRefreshToken(action.refresh_token);
            //
            return { ...state };
        case ApplicationContextActionType.LOGOUT: // 用户退出登陆
            state.authenticated = false;
            state.accessToken = null;
            state.refreshToken = null;
            state.principal = null;
            state.preference = initialPreference;
            storageService.removeAccessToken();
            storageService.removeRefreshToken();
            return { ...state };
        case ApplicationContextActionType.PRINCIPAL: // 设置用户信息
            state.principal = action.principal;
            return { ...state };
        case ApplicationContextActionType.TOGGLE_THEME: // 切换主题
            return { ...state };
        case ApplicationContextActionType.TOGGLE_SIDER: // 切换侧边栏
            state.preference.sidebarCollapsed = !state.preference.sidebarCollapsed;
            return { ...state };
        case ApplicationContextActionType.CHANGE_LOCALE: // 切换语言
            state.preference.locale = action.locale;
            return { ...state };
        default:
            return { ...state };
    }
};

/**
 * ApplicationStore
 */
export interface ApplicationStore {
    state: ApplicationState;
    dispatch?: React.Dispatch<ApplicationContextAction>;
}

export const ApplicationContext = createContext<ApplicationStore>({ state: initialApplicationState });

ApplicationContext.displayName = 'Application Global Context';

export const useApplicationContext = (): ApplicationStore => useContext<ApplicationStore>(ApplicationContext);

export const ApplicationProvider = (props: {
    state?: ApplicationState;
    children?: React.ReactNode;
}): React.ReactElement => {
    const [state, dispatch] = useReducer(reducer, props.state);
    return <ApplicationContext.Provider value={{ state, dispatch }}>{props.children}</ApplicationContext.Provider>;
};

/**
 * 初始化
 */
export const initialize = async (): Promise<ApplicationState> => {
    const accessToken = storageService.getAccessToken();
    const refreshToken = storageService.getRefreshToken();
    if (isEmpty(accessToken) || isEmpty(refreshToken)) {
        const applicationState = initialApplicationState;
        applicationState.preference.locale = getBrowserLocale();
        return applicationState;
    } else {
        // 已存在刷新凭证和访问凭证的情况下发送请求获取用户信息
        return UserService.user().then(async (resp: AxiosResponse<Principal>) => {
            return {
                authenticated: true,
                accessToken: accessToken,
                refreshToken: refreshToken,
                principal: resp.data,
                preference: initialPreference,
            };
        });
    }
};
