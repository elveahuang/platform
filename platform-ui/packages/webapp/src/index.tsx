import React from 'react';
import ReactDOM from 'react-dom';
//
import App from '@/App';
//
import '@/index.scss';
import { setTheme } from '@commons/utils/theme';

setTheme();

ReactDOM.render(<App />, document.getElementById('root'));
