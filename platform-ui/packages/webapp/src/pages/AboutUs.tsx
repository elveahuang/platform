import React, { FC } from 'react';
//
import './AboutUs.scss';
import { SketchPicker } from 'react-color';

const AboutUs: FC = () => {
    return (
        <div className="text-center">
            <SketchPicker />
            <div className="text-center">About Us</div>;
        </div>
    );
};

export default AboutUs;
