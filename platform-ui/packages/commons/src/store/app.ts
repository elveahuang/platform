import { createSlice } from '@reduxjs/toolkit';
import { defaultLang, LangType } from '@commons/types';
import { isDev } from '@commons/utils';
import { applicationVersion } from '@commons/constants';

export interface AppState {
    isLoading: boolean;
    lang: LangType;
}

export const initialAppState: AppState = {
    isLoading: false,
    lang: defaultLang,
};

export const appSlice = createSlice({
    name: 'app',
    initialState: initialAppState,
    reducers: {
        init: (state: AppState) => {
            if (isDev) {
                console.log(`Current Version = ${applicationVersion}`);
            }
        },
    },
});

export const { init } = appSlice.actions;

export default appSlice.reducer;
