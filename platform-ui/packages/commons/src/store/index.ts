import appReducer from './app';
import userReducer from './user';
import { configureStore } from '@reduxjs/toolkit';

export const store = configureStore({
    reducer: {
        app: appReducer,
        user: userReducer,
    },
});

export type RootState = ReturnType<typeof store.getState>;

export type RootDispatch = typeof store.dispatch;

export default store;

export { userReducer, appReducer };
