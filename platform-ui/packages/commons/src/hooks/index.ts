import { useDispatch, useSelector } from 'react-redux';
import { RootDispatch, RootState } from '@commons/store';

//
export const useAppDispatch = () => useDispatch<RootDispatch>();

//
export const useAppSelector = <T = any>(selector: (state: RootState) => T): T => {
    return useSelector(selector);
};
