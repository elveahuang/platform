const config = require('./config');

module.exports = {
    purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
    darkMode: false,
    important: true,
    theme: {
        extend: {
            colors: {
                primary: config.primaryColor,
            },
        },
        screens: {
            xs: { max: '575px' },
            sm: { min: '576px', max: '767px' },
            md: { min: '768px', max: '991px' },
            lg: { min: '992px', max: '1199px' },
            xl: { min: '1200px', max: '1599px' },
            xxl: { min: '1600px' },
        },
    },
    variants: {
        extend: {},
    },
    plugins: [],
};
