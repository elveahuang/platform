import React, { FC } from 'react';
//
import './Home.scss';

const Home: FC = () => {
    return (
        <div className="home-container">
            <div className="home-banner">
                <div className="home-banner-background" />
                <div className="home-banner-holder">
                    <div className="home-banner-content">Banner</div>
                </div>
            </div>
            <div className="text-center" style={{ height: 300 }}>
                Home 1
            </div>
        </div>
    );
};

export default Home;
