import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { defaultLang, LangType } from '@commons/utils/i18n';
import { isDev } from '@commons/utils';
import { applicationVersion } from '@commons/constants';

export interface AppState {
    isLoading: boolean;
    lang: LangType;
    timeZone: string;
    sidebarCollapsed: boolean;
}

export const initialAppState: AppState = {
    isLoading: false,
    lang: defaultLang,
    timeZone: '',
    sidebarCollapsed: false,
};

export const appSlice = createSlice({
    name: 'app',
    initialState: initialAppState,
    reducers: {
        /**
         * 初始化应用
         */
        initialize: (state: AppState) => {
            if (isDev) {
                console.log(`Current Version = ${applicationVersion}`);
            }
        },
        /**
         * 初始化应用
         */
        changeLang: (state: AppState, action: PayloadAction<LangType>) => {
            return { ...state, lang: action.payload };
        },
        /**
         * 初始化应用
         */
        toggleSidebar: (state: AppState, action: PayloadAction<boolean>) => {
            return { ...state, sidebarCollapsed: action.payload };
        },
    },
});

export const { initialize, changeLang, toggleSidebar } = appSlice.actions;

export default appSlice.reducer;
