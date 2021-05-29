import React from 'react';
import AdminLayout from '../../components/Layouts/AdminLayout';
//
import './Dashboard.css';
import { Pagination } from 'antd';
import { useApplicationContext } from '@/context/Application';
import { FormattedMessage } from 'react-intl';

const Dashboard = (): React.ReactElement => {
    const { state } = useApplicationContext();
    return (
        <AdminLayout>
            <FormattedMessage id={'site_title'} />
            <div>{state.preference.sidebarCollapsed ? '1' : '0'}</div>
            <div>{state.preference.locale}</div>
            <Pagination defaultCurrent={1} total={50} showSizeChanger />
        </AdminLayout>
    );
};

export default Dashboard;
