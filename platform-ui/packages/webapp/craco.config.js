const path = require('path');
const fs = require('fs');
const CracoExtendScope = require('@dvhb/craco-extend-scope');
const CracoBabelLoader = require('craco-babel-loader');
const CracoLessPlugin = require('craco-less');
const CracoAntDesignPlugin = require('craco-antd');
const CracoAlias = require('craco-alias');
const config = require('./config');
//
const appDirectory = fs.realpathSync(process.cwd());
const resolvePackage = (relativePath) => path.resolve(appDirectory, relativePath);

module.exports = {
    plugins: [
        {
            plugin: CracoAntDesignPlugin,
        },
        {
            plugin: CracoLessPlugin,
            options: {
                lessLoaderOptions: {
                    modifyVars: { '@primary-color': config.primaryColor },
                    javascriptEnabled: true,
                },
            },
        },
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
