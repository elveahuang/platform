const path = require('path');
const fs = require('fs');
const CracoAliasPlugin = require('craco-alias');
const CracoExtendScopePlugin = require('@dvhb/craco-extend-scope');
const CracoBabelLoaderPlugin = require('craco-babel-loader');
const WebpackBar = require('webpackbar');
const { BundleAnalyzerPlugin } = require('webpack-bundle-analyzer');
const CaseSensitivePathsPlugin = require('case-sensitive-paths-webpack-plugin');
//
const appDirectory = fs.realpathSync(process.cwd());
const resolvePackage = (relativePath) => path.resolve(appDirectory, relativePath);

module.exports = {
    style: {
        postcss: {
            plugins: [require('tailwindcss'), require('autoprefixer')],
        },
    },
    webpack: {
        plugins: [
            new CaseSensitivePathsPlugin(),
            new WebpackBar(),
            new BundleAnalyzerPlugin({
                analyzerPort: 'auto',
            }),
        ],
    },
    plugins: [
        {
            plugin: CracoAliasPlugin,
            options: {
                source: 'tsconfig',
                tsConfigPath: './tsconfig.base.json',
            },
        },
        {
            plugin: CracoExtendScopePlugin,
            options: {
                path: '../commons/src',
            },
        },
        {
            plugin: CracoBabelLoaderPlugin,
            options: {
                includes: [resolvePackage('../commons/src')],
            },
        },
    ],
};
