import { resolve } from 'node:path';
import { execTask } from './utils/index.mjs';

const root = resolve(process.cwd());
console.log(`Current workspace - ${root}`);
// 编译构建
await execTask(`pnpm run build`, root);
