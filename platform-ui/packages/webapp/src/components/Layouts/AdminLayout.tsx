import React from 'react';
import { FormattedMessage } from 'react-intl';
import { Col, Dropdown, Layout, Menu, Row } from 'antd';
import {
    MenuFoldOutlined,
    MenuUnfoldOutlined,
    UploadOutlined,
    UserOutlined,
    VideoCameraOutlined,
} from '@ant-design/icons';
//
import './AdminLayout.less';
import { ApplicationContextActionType, useApplicationContext } from '@/context/Application';
import { MenuInfo } from 'rc-menu/lib/interface';
import { availableApplicationLocales } from '@commons/constants';
import { useHistory } from 'react-router';

type AdminLayoutProps = {
    layoutClassName?: string;
    children?: React.ReactNode;
};

const AdminLayout = (props: AdminLayoutProps): React.ReactElement => {
    const history = useHistory();
    const { state, dispatch } = useApplicationContext();

    const handleAccountMenuChange = (info: MenuInfo) => {
        switch (info.key) {
            case 'logout':
                dispatch({
                    type: ApplicationContextActionType.LOGOUT,
                });
                history.push('/');
                break;
            default:
                break;
        }
    };

    const handleChangeLocale = (info: MenuInfo) => {
        console.log('click ', info.key);

        let selectedLocale;
        switch (info.key) {
            case 'lang_en_us':
                selectedLocale = availableApplicationLocales.EN_US;
                break;
            case 'label_zh_cn':
                selectedLocale = availableApplicationLocales.ZH_CN;
                break;
            case 'label_zh_tw':
                selectedLocale = availableApplicationLocales.ZH_TW;
                break;
            default:
                selectedLocale = availableApplicationLocales.EN_US;
                break;
        }
        dispatch({
            type: ApplicationContextActionType.CHANGE_LOCALE,
            locale: selectedLocale,
        });
    };
    return (
        <Layout className="admin-layout">
            <Layout.Sider className="admin-layout-sider" collapsed={state.preference.sidebarCollapsed}>
                <div className="admin-layout-logo" />
                <Menu theme="dark" mode="inline" defaultSelectedKeys={['4']}>
                    <Menu.Item key="1" icon={<UserOutlined />}>
                        nav 1
                    </Menu.Item>
                    <Menu.Item key="2" icon={<VideoCameraOutlined />}>
                        nav 2
                    </Menu.Item>
                    <Menu.Item key="3" icon={<UploadOutlined />}>
                        nav 3
                    </Menu.Item>
                    <Menu.Item key="4" icon={<UserOutlined />}>
                        nav 4
                    </Menu.Item>
                </Menu>
            </Layout.Sider>

            <Layout className="admin-content-layout">
                <Layout.Header className="admin-content-layout-header">
                    <Row justify="end">
                        <Col flex="64px">
                            {React.createElement(
                                state.preference.sidebarCollapsed ? MenuUnfoldOutlined : MenuFoldOutlined,
                                {
                                    className: 'trigger',
                                    onClick: () => {
                                        dispatch({
                                            type: ApplicationContextActionType.TOGGLE_SIDER,
                                        });
                                    },
                                },
                            )}
                        </Col>
                        <Col flex="auto">
                            <div className="admin-content-layout-header-nav">
                                <Dropdown
                                    overlay={
                                        <Menu className="drowdown-menu" onClick={handleAccountMenuChange}>
                                            <Menu.Item className="drowdown-menu-item" key="account">
                                                <UserOutlined />
                                                <span className="drowdown-menu-label">
                                                    <FormattedMessage id={'user_label_account'} />
                                                </span>
                                            </Menu.Item>
                                            <Menu.Item className="drowdown-menu-item" key="change-password">
                                                <UserOutlined />
                                                <span className="drowdown-menu-label">
                                                    <FormattedMessage id={'user_label_change_password'} />
                                                </span>
                                            </Menu.Item>
                                            <Menu.Item className="drowdown-menu-item" key="preferences">
                                                <UserOutlined />
                                                <span className="drowdown-menu-label">
                                                    <FormattedMessage id={'user_label_preferences'} />
                                                </span>
                                            </Menu.Item>
                                            <Menu.Divider />
                                            <Menu.Item className="drowdown-menu-item" key="logout">
                                                <UserOutlined />
                                                <span className="drowdown-menu-label">
                                                    <FormattedMessage id={'label_logout'} />
                                                </span>
                                            </Menu.Item>
                                        </Menu>
                                    }
                                    placement="bottomRight"
                                >
                                    <span className="action account">
                                        <UserOutlined className="avatar" />
                                        <span className="name">{state.principal.nickname}</span>
                                    </span>
                                </Dropdown>
                                <Dropdown
                                    overlay={
                                        <Menu className="drowdown-menu" onClick={handleChangeLocale}>
                                            <Menu.Item className="drowdown-menu-item" key="lang_en_us">
                                                <UserOutlined />
                                                <span className="drowdown-menu-label">
                                                    <FormattedMessage id={'label_en_us'} />
                                                </span>
                                            </Menu.Item>
                                            <Menu.Item className="drowdown-menu-item" key="label_zh_cn">
                                                <UserOutlined />
                                                <span className="drowdown-menu-label">
                                                    <FormattedMessage id={'label_zh_cn'} />
                                                </span>
                                            </Menu.Item>
                                            <Menu.Item className="drowdown-menu-item" key="label_zh_tw">
                                                <UserOutlined />
                                                <span className="drowdown-menu-label">
                                                    <FormattedMessage id={'label_zh_tw'} />
                                                </span>
                                            </Menu.Item>
                                        </Menu>
                                    }
                                    placement="bottomRight"
                                >
                                    <span className="action account">
                                        <span className="name">
                                            <FormattedMessage id={'label_language'} />
                                        </span>
                                    </span>
                                </Dropdown>
                            </div>
                        </Col>
                    </Row>
                </Layout.Header>
                <Layout.Content className="admin-content-layout-main">{props.children}</Layout.Content>
            </Layout>
        </Layout>
    );
};

export default AdminLayout;
