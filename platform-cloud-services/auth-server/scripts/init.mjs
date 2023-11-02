import { resolve } from 'node:path';
import { execTask } from './utils/index.mjs';

const root = resolve(process.cwd());
console.log(`Current workspace - ${root}`);
// 安装模块依赖
await execTask(`pnpm install`, root);
