import React, { FC, lazy, Suspense } from 'react';
import { Col, Layout, Menu, Row, Space } from 'antd';
import { Link, Route, Switch } from 'react-router-dom';
import { useHistory } from 'react-router';
import classNames from 'classnames';
import { useIntl } from 'react-intl';
//
import images from '@commons/utils/images';
import './MainLayout.scss';
import { Loading, ThemeSwitch } from '@commons/webapp/components';
import DirectionSwitch from '@commons/webapp/components/Theme/DirectionSwitch';
//
const Home = lazy(() => import('@/pages/Home'));
const AboutUs = lazy(() => import('@/pages/AboutUs'));

const MainLayout: FC = () => {
    const intl = useIntl();
    const { location } = useHistory();
    const layoutClassName = classNames('main-layout', location.pathname === '/' ? 'home-layout' : null);

    return (
        <Layout className={layoutClassName}>
            <Layout.Header id="header" className="header">
                <Row className="header-row">
                    <Col md={6} sm={24}>
                        <a className="header-logo">
                            <img alt="logo" src={images.logo} />
                            <span>Application</span>
                        </a>
                    </Col>
                    <Col md={18} sm={0}>
                        <div className="header-menu">
                            <Menu mode="horizontal">
                                <Menu.Item key="menu-home">
                                    <Link to="/">Home</Link>
                                </Menu.Item>
                                <Menu.Item key="menu-about-us">
                                    <Link to="/about-us">About Us</Link>
                                </Menu.Item>
                                <Menu.Item key="login">
                                    <Link to="/login">Login</Link>
                                </Menu.Item>
                            </Menu>
                        </div>
                    </Col>
                </Row>
            </Layout.Header>
            <Layout.Content>
                <Suspense fallback={<Loading />}>
                    <Switch>
                        <Route exact path="/" component={Home} />
                        <Route exact path="/about-us" component={AboutUs} />
                    </Switch>
                </Suspense>
            </Layout.Content>
            <Layout.Footer id="footer">
                {intl.formatMessage(
                    {
                        id: 'site_copyright',
                    },
                    {
                        curDate: new Date(),
                    },
                )}
                <Space />
                <ThemeSwitch />
                <DirectionSwitch />
            </Layout.Footer>
        </Layout>
    );
};

export default MainLayout;
