import { Client } from '@stomp/stompjs';
import { setupHttp } from '@commons/webapp/utils/http';
import { setTheme } from '@commons/utils/theme';
import env from '@commons/utils/env';

export const setup = () => {
    setTheme();
    setupHttp();

    const token =
        'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVpZCI6MSwibmJmIjoxNjI1NDExNTU0LCJzZXNzaW9uX2lkIjoiMDQyZGMyMGQtYjkwNC00YzgzLWEyODYtOTI3ZWRhZjRkZjUxIiwidHlwZSI6ImFjY2Vzc190b2tlbiIsImV4cCI6MTYyNTQxNTE1NCwiaWF0IjoxNjI1NDExNTU0LCJqdGkiOiIwNDJkYzIwZC1iOTA0LTRjODMtYTI4Ni05MjdlZGFmNGRmNTEiLCJhY2NvdW50IjoiYWRtaW4ifQ.by1sO8kco5SrXsxfJ_ylXQPstSt6bY-9j1Z9wNFT6t4';
    const webSocketClient = new Client({
        brokerURL: `${env.webSocketServer}web-socket?code=${token}&t=${Math.random()}`,
        reconnectDelay: 6000,
        heartbeatIncoming: 6000,
        heartbeatOutgoing: 6000,
    });
    webSocketClient.onConnect = function (frame) {
        console.log('Web Socket connected.');
        webSocketClient.subscribe('/topic/message', function (e) {
            console.log('topic - message', e);
        });
    };
    webSocketClient.onStompError = (frame) => {
        console.log('Web Socket Error : ' + frame);
        console.log(frame);
    };
    webSocketClient.activate();
};
