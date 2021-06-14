import React, { FC } from 'react';
import { Pagination } from 'antd';
import { FormattedMessage } from 'react-intl';
//
import './Dashboard.scss';
import { useAppSelector } from '@/hooks';

const Dashboard: FC = () => {
    const state = useAppSelector((state) => state);
    return (
        <>
            <FormattedMessage id={'site_title'} />
            <div>{state.app.sidebarCollapsed ? '1' : '0'}</div>
            <div>{state.app.lang}</div>
            <Pagination defaultCurrent={1} total={50} showSizeChanger />
        </>
    );
};

export default Dashboard;
