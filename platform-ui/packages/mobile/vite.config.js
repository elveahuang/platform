import { defineConfig, loadEnv } from 'vite';
import reactRefresh from '@vitejs/plugin-react-refresh';
import tsconfigPaths from 'vite-tsconfig-paths';

export default ({ command, mode }) => {
    const env = loadEnv(mode, process.cwd());
    console.log(`command - ${command}. mode - ${mode}.`);
    console.log(env);

    const config = defineConfig({
        base: env.VITE_APP_BASE ?? '/',
        server: {
            port: env.VITE_APP_PORT ?? 8081,
        },
        resolve: {
            alias: {
                '~@ionic': '@ionic',
                '~video.js': 'video.js',
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

    if (command === 'build' && mode === 'watch') {
        return {
            ...config,
            build: {
                watch: {},
                outDir: env.VITE_APP_DIST ?? 'dist',
            },
        };
    }
    return config;
};
