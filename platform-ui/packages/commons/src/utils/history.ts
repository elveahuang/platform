import { createHashHistory, Path } from 'history';
import { parse, stringify } from 'qs';

const history = createHashHistory();

const navigate = (path: Path, queryParams?: Record<string, any>, replace: boolean = false): void => {
    const pushOrReplace = replace ? history.replace : history.push;
    pushOrReplace({ pathname: path, search: stringify(queryParams) });
};

const navigateBack = (path: Path, queryParams?: Record<string, any>, replace: boolean = false) => {
    const pushOrReplace = replace ? history.replace : history.push;
    pushOrReplace({ pathname: path, search: stringify(queryParams), state: { direction: 'back' } });
};

const goBack = (): void => {
    history.goBack();
};

const getPageQuery = <T extends Record<keyof T, string> = any>(): Record<keyof T, string> =>
    parse(window.location.href.split('?')[1]) as Record<keyof T, string>;

export default history;

export { navigate, goBack, getPageQuery, navigateBack };
