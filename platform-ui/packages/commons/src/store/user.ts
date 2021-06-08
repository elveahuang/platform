import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import Principal from '@commons/types/principal';
import storageService from '@commons/services/storage.service';

interface UserState {
    accessToken: string;
    refreshToken: string;
    principal: Principal;
}

const initialUserState: UserState = {
    accessToken: storageService.getAccessToken() || null,
    refreshToken: storageService.getRefreshToken() || null,
    principal: null,
};

export const userSlice = createSlice({
    name: 'user',
    initialState: initialUserState,
    reducers: {
        setAccessToken: (state: UserState, action: PayloadAction<string>) => {
            storageService.setAccessToken(action.payload);
            return { ...state, accessToken: action.payload };
        },
        setRefreshToken: (state: UserState, action: PayloadAction<string>) => {
            storageService.setRefreshToken(action.payload);
            return { ...state, refreshToken: action.payload };
        },
        setUser: (state: UserState, action: PayloadAction<Principal>) => {
            return { ...state, principal: action.payload };
        },
        clear: (state: UserState) => {
            storageService.removeAccessToken();
            storageService.removeRefreshToken();
            return { ...state, principal: null, refreshToken: null, accessToken: null };
        },
    },
});

export const { setAccessToken, setRefreshToken, setUser, clear } = userSlice.actions;

export default userSlice.reducer;
