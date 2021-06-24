import { defineConfig } from 'vite';
import reactRefresh from '@vitejs/plugin-react-refresh';
import tsconfigPaths from 'vite-tsconfig-paths';
import createImportPlugin from 'vite-plugin-import';

export default defineConfig({
    server: {
        port: 8082,
    },
    resolve: {
        alias: {
            '~@ionic': '@ionic',
        },
    },
    plugins: [
        reactRefresh(),
        tsconfigPaths(),
        createImportPlugin([
            {
                libraryName: 'antd',
                style: 'css',
            },
        ]),
    ],
});
