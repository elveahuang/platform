import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import Principal from '@commons/types/principal';
import StorageService from '@commons/services/StorageService';

interface UserState {
    authenticated: boolean;
    accessToken: string;
    refreshToken: string;
    principal: Principal;
}

const initialUserState: UserState = {
    authenticated: false,
    accessToken: StorageService.getAccessToken() || null,
    refreshToken: StorageService.getRefreshToken() || null,
    principal: null,
};

export const userSlice = createSlice({
    name: 'user',
    initialState: initialUserState,
    reducers: {
        setAccessToken: (state: UserState, action: PayloadAction<string>) => {
            StorageService.setAccessToken(action.payload);
            return { ...state, accessToken: action.payload };
        },
        setRefreshToken: (state: UserState, action: PayloadAction<string>) => {
            StorageService.setRefreshToken(action.payload);
            return { ...state, refreshToken: action.payload };
        },
        setUser: (state: UserState, action: PayloadAction<Principal>) => {
            state.authenticated = true;
            state.principal = action.payload;
            return state;
        },
        clear: (state: UserState) => {
            StorageService.removeAccessToken();
            StorageService.removeRefreshToken();
            return { ...state, authenticated: false, principal: null, refreshToken: null, accessToken: null };
        },
    },
});

export const { setAccessToken, setRefreshToken, setUser, clear } = userSlice.actions;

export default userSlice.reducer;
