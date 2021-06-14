import React, { FC } from 'react';
import { Spin } from 'antd';

const Loading: FC = () => {
    return (
        <div
            style={{
                width: '100%',
                height: '100%',
                display: 'flex',
                justifyContent: 'center',
            }}
        >
            <Spin size="large" />
        </div>
    );
};

export default Loading;
