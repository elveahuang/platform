import React from 'react';
import ReactDOM from 'react-dom';
//
import '@/index.scss';
import App from '@/App';
import env from '@commons/utils/env';

console.log(`env.mock.enabled - ${env.mock.enabled}`);
console.log(typeof env.mock.enabled);
console.log(`env.xdebug.enabled - ${env.xdebug.enabled}`);
console.log(typeof env.xdebug.enabled);
if (env.mock.enabled) {
    console.log(`Mock enabled - ${env.mock.enabled}`);
    import('@commons/mock').then(() => {
        ReactDOM.render(<App />, document.getElementById('root'));
    });
} else {
    ReactDOM.render(<App />, document.getElementById('root'));
}
