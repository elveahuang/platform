import { setupHttp } from '@commons/webapp/utils/http';
import { setTheme } from '@commons/utils/theme';

export const setup = () => {
    setTheme();
    setupHttp();
};
