import React from 'react';
import { Redirect, Route } from 'react-router-dom';
import { IonApp, IonIcon, IonLabel, IonRouterOutlet, IonTabBar, IonTabButton, IonTabs } from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import { ellipse, square, triangle } from 'ionicons/icons';
//
import Home from '@/pages/Home';
import Discover from '@/pages/Discover';
import Me from '@/pages/Me';

/**
 * App
 */
const App: React.FC = () => (
    <IonApp>
        <IonReactRouter>
            <IonTabs>
                <IonRouterOutlet>
                    <Route path="/home" component={Home} exact={true} />
                    <Route path="/discover" component={Discover} exact={true} />
                    <Route path="/me" component={Me} />
                    <Route path="/" render={() => <Redirect to="/home" />} exact={true} />
                </IonRouterOutlet>
                <IonTabBar slot="bottom">
                    <IonTabButton tab="home" href="/home">
                        <IonIcon icon={triangle} />
                        <IonLabel>Home</IonLabel>
                    </IonTabButton>
                    <IonTabButton tab="discover" href="/discover">
                        <IonIcon icon={ellipse} />
                        <IonLabel>Discover</IonLabel>
                    </IonTabButton>
                    <IonTabButton tab="me" href="/me">
                        <IonIcon icon={square} />
                        <IonLabel>Me</IonLabel>
                    </IonTabButton>
                </IonTabBar>
            </IonTabs>
        </IonReactRouter>
    </IonApp>
);

export default App;
