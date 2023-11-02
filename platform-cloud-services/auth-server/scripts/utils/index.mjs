import { exec } from 'node:child_process';
import { chdir } from 'node:process';
import { resolve } from 'node:path';
import { existsSync } from 'node:fs';
import { rm } from 'fs/promises';

export const updateModule = async (path) => {
    console.log(`Update module [${path}]...`);
    chdir(path);
    if (existsSync(resolve(path, 'package-lock.json'))) {
        await rm(resolve(path, 'package-lock.json'));
    }
    if (existsSync(resolve(path, 'pnpm-lock.yaml'))) {
        await rm(resolve(path, 'pnpm-lock.yaml'));
    }
    if (existsSync(resolve(path, 'node_modules'))) {
        await rm(resolve(path, 'node_modules'), { recursive: true });
    }
    await execTask(`ncu -u`, path);
};

export const execTask = async (task, path) => {
    console.log(`execTask [${task}] start.`);
    return new Promise((resolve, reject) => {
        exec(task, { cwd: path }, (e) => {
            if (e) {
                console.log(e);
                reject();
            } else {
                console.log(`execTask [${task}] done.`);
                resolve();
            }
        });
    });
};
