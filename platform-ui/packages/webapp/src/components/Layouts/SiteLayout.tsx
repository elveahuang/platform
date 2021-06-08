import React from 'react';
import { Link } from 'react-router-dom';
import { Col, Layout, Menu, Row } from 'antd';
import classNames from 'classnames';
//
import { Image_Logo } from '@/utils/images';
//
import './SiteLayout.less';

type SiteLayoutProps = {
    layoutClassName?: string;
    children?: React.ReactNode;
};

const SiteLayout = (props: SiteLayoutProps): React.ReactElement => {
    const layoutClassName = classNames('site-layout', props.layoutClassName || '');

    return (
        <Layout className={layoutClassName}>
            <Layout.Header id="header" className="header">
                <Row className="header-row">
                    <Col md={6} sm={24}>
                        <a className="header-logo">
                            <img alt="logo" src={Image_Logo} />
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
            <Layout.Content>{props.children}</Layout.Content>
            <Layout.Footer id="footer">Copyright&copy;2020</Layout.Footer>
        </Layout>
    );
};

export default SiteLayout;
