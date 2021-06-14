import { defineConfig } from 'vite';
import reactRefresh from '@vitejs/plugin-react-refresh';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
    resolve: {
        alias: {
            '~antd': 'antd',
        },
    },
    css: {
        preprocessorOptions: {
            less: {
                javascriptEnabled: true,
            },
        },
    },
    plugins: [reactRefresh(), tsconfigPaths()],
});
