import { setupHttp } from '@commons/webapp/utils/http';
import { setTheme } from '@commons/utils/theme';

const setup = () => {
    setTheme();
    setupHttp();
};

export default setup;
