import appReducer from './app';
import userReducer from './user';
import { configureStore } from '@reduxjs/toolkit';
import { Store } from 'redux';

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

//

let globalStore: Store;

export function createStore(reducers: any) {
    globalStore = configureStore({
        reducer: {
            app: appReducer,
            user: userReducer,
            ...reducers,
        },
    });
    return globalStore;
}
