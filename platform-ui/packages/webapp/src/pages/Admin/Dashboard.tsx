import React from 'react';
import AdminLayout from '../../components/Layouts/AdminLayout';
//
import './Dashboard.css';
import { Pagination } from 'antd';
import { FormattedMessage } from 'react-intl';
import { useAppSelector } from '@/hooks';

const Dashboard = (): React.ReactElement => {
    const state = useAppSelector((state) => state);
    return (
        <AdminLayout>
            <FormattedMessage id={'site_title'} />
            <div>{state.app.sidebarCollapsed ? '1' : '0'}</div>
            <div>{state.app.lang}</div>
            <Pagination defaultCurrent={1} total={50} showSizeChanger />
        </AdminLayout>
    );
};

export default Dashboard;
