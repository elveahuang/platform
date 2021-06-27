module.exports = {
    purge: ['./src/**/*.{js,jsx,ts,tsx}'],
    darkMode: false,
    important: true,
    theme: {
        extend: {
            colors: {
                'color-primary': 'var(--color-primary)',
                'color-secondary': 'var(--color-secondary)',
            },
        },
    },
};
