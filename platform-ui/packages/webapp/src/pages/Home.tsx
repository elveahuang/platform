import React from 'react';
import { SiteLayout } from '../components/Layouts';
//
import './Home.less';
import { Button } from 'antd';

const Home = (): React.ReactElement => {
    return (
        <SiteLayout layoutClassName="home-layout">
            <div className="home-container">
                <div className="home-banner">
                    <div className="home-banner-background" />
                    <div className="home-banner-holder">
                        <div className="home-banner-content">Banner</div>
                    </div>
                </div>
                <div className="text-center">
                    <Button type="primary">Primary Button</Button>
                    <div className="home-content">Content</div>
                </div>
            </div>
        </SiteLayout>
    );
};

export default Home;
