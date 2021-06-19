import React from 'react';
import ReactDOM from 'react-dom';
//
import '@/index.scss';
import App from '@/App';
import setup from '@/utils';

setup();

ReactDOM.render(<App />, document.getElementById('root'));
