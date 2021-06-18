module.exports = {
    root: true,
    extends: ['react-app', 'prettier', 'plugin:jsx-a11y/recommended'],
    plugins: ['jsx-a11y'],
    rules: {
        'jsx-a11y/anchor-is-valid': 'off',
    },
};
