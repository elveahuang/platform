import React, { FC } from 'react';
import { Spin } from 'antd';
//
import './index.scss';

const Loading: FC = () => {
    return (
        <div className="loading-div-container">
            <Spin size="large" />
        </div>
    );
};

export default Loading;
