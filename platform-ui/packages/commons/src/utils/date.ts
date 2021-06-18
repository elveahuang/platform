import { isUndefined } from 'lodash';
import { format } from 'date-fns';

/**
 * 格式化日期
 */
export function formatDatetime(date: Date | number): string {
    if (!isUndefined(date)) {
        return format(date, 'YYYY-MM-DD HH:mm');
    }
    return '--';
}

/**
 * 格式化日期
 */
export function formatDate(date: Date | number): string {
    if (!isUndefined(date)) {
        return format(date, 'YYYY-MM-DD');
    }
    return '--';
}

/**
 * 格式化年份
 */
export function formatYear(date: Date | number): string {
    if (!isUndefined(date)) {
        return format(date, 'YYYY');
    }
    return '--';
}
