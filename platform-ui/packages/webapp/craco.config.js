const path = require('path');
const fs = require('fs');
const CracoAlias = require('craco-alias');
const CracoExtendScope = require('@dvhb/craco-extend-scope');
const CracoBabelLoader = require('craco-babel-loader');
const WebpackBar = require('webpackbar');
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
        plugins: [new CaseSensitivePathsPlugin(), new WebpackBar()],
    },
    plugins: [
        {
            plugin: CracoAlias,
            options: {
                source: 'tsconfig',
                tsConfigPath: './tsconfig.base.json',
            },
        },
        {
            plugin: CracoExtendScope,
            options: {
                path: '../commons/src',
            },
        },
        {
            plugin: CracoBabelLoader,
            options: {
                includes: [resolvePackage('../commons/src')],
            },
        },
    ],
};
