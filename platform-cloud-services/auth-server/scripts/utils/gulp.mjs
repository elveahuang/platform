import { resolve } from 'node:path';
import gulpFileSync from 'gulp-file-sync';
import gulp from 'gulp';

export const root = resolve(process.cwd());

export const paths = {
    resources: {
        nmp: resolve(root, 'node_modules'),
        public: resolve(root, 'src/main/resources/public'),
        static: resolve(root, 'src/main/resources/static'),
        templates: resolve(root, 'src/main/resources/templates'),
        libs: resolve(root, 'src/main/resources/public/static'),
    },
    dist: {
        public: resolve(root, 'build/resources/main/public'),
        static: resolve(root, 'build/resources/main/static'),
        templates: resolve(root, 'build/resources/main/templates'),
    },
};

export const syncStatic = async () => {
    console.log('sync static...');
    await gulpFileSync(paths.resources.static, paths.dist.static, { recursive: true });
    console.log('sync static done.');
};

export const syncPublic = async () => {
    console.log('sync public...');
    await gulpFileSync(paths.resources.public, paths.dist.public, { recursive: true });
    console.log('sync public done.');
};

export const syncTemplate = async () => {
    console.log('sync template...');
    await gulpFileSync(paths.resources.templates, paths.dist.templates, { recursive: true });
    console.log('sync template done.');
};

export const sync = async () => {
    console.log('sync...');
    await syncStatic();
    await syncPublic();
    await syncTemplate();
    console.log('sync done.');
};

export const dev = async () => {
    console.log('dev...');
    gulp.watch([paths.resources.static], gulp.series(syncStatic));
    gulp.watch([paths.resources.public], gulp.series(syncPublic));
    gulp.watch([paths.resources.templates], gulp.series(syncTemplate));
};
