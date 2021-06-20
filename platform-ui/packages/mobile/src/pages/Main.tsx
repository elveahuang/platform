import React, { FC } from 'react';
import { Redirect, Route } from 'react-router-dom';
import { IonIcon, IonLabel, IonRouterOutlet, IonTabBar, IonTabButton, IonTabs } from '@ionic/react';
import { ellipse, square, triangle } from 'ionicons/icons';
//
import '@/App.scss';
import Home from '@/pages/Home';
import Discover from '@/pages/Discover';
import Me from '@/pages/Me';

const Main: FC = () => (
    <IonTabs>
        <IonRouterOutlet>
            <Redirect path="/tabs" to="/tabs/home" exact={true} />
            <Route path="/tabs/home" component={Home} exact={true} />
            <Route path="/tabs/discover" component={Discover} exact={true} />
            <Route path="/tabs/me" component={Me} />
        </IonRouterOutlet>
        <IonTabBar slot="bottom">
            <IonTabButton tab="home" href="/tabs/home">
                <IonIcon icon={triangle} />
                <IonLabel>Home</IonLabel>
            </IonTabButton>
            <IonTabButton tab="discover" href="/tabs/discover">
                <IonIcon icon={ellipse} />
                <IonLabel>Discover</IonLabel>
            </IonTabButton>
            <IonTabButton tab="me" href="/tabs/me">
                <IonIcon icon={square} />
                <IonLabel>Me</IonLabel>
            </IonTabButton>
        </IonTabBar>
    </IonTabs>
);

export default Main;
