import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { defaultLang, LangType } from '@commons/utils/i18n';
import { applicationVersion } from '@commons/constants';
import { DirectionType, setTheme, ThemeType } from '@commons/utils/theme';
import env from '@commons/utils/env';

export interface AppState {
    isLoading: boolean;
    lang: LangType;
    timeZone: string;
    sidebarCollapsed: boolean;
    theme: ThemeType;
    direction: DirectionType;
}

export const initialAppState: AppState = {
    isLoading: false,
    lang: defaultLang,
    timeZone: '',
    sidebarCollapsed: false,
    theme: ThemeType.DEFAULT,
    direction: DirectionType.LTR,
};

export const appSlice = createSlice({
    name: 'app',
    initialState: initialAppState,
    reducers: {
        /**
         * 初始化应用
         */
        initialize: (state: AppState) => {
            if (!env.production) {
                console.log(`Current Version = ${applicationVersion}`);
            }
            return state;
        },
        /**
         * 切换语言
         */
        changeLang: (state: AppState, action: PayloadAction<LangType>) => {
            return { ...state, lang: action.payload };
        },
        /**
         * 切换主题
         */
        changeTheme: (state: AppState, action: PayloadAction<ThemeType>) => {
            setTheme(action.payload);
            return { ...state, theme: action.payload };
        },
        /**
         * 切换对其方式
         */
        changeDirection: (state: AppState, action: PayloadAction<DirectionType>) => {
            return { ...state, direction: action.payload };
        },
        /**
         * 初始化应用
         */
        toggleSidebar: (state: AppState, action: PayloadAction<boolean>) => {
            return { ...state, sidebarCollapsed: action.payload };
        },
    },
});

export const { initialize, changeLang, changeTheme, changeDirection, toggleSidebar } = appSlice.actions;

export default appSlice.reducer;
