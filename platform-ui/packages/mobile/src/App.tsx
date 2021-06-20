import React, { FC, useEffect, useState } from 'react';
import { Redirect, Route } from 'react-router-dom';
import { IonApp, IonRouterOutlet } from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import { RawIntlProvider } from 'react-intl';
import { Provider } from 'react-redux';
//
import '@/App.scss';
import history from '@commons/utils/history';
import setup from '@/utils';
import Login from '@/pages/Login';
import Main from '@/pages/Main';
import store from '@commons/store';
import { useAppSelector } from '@commons/hooks';
import { createReactIntl } from '@commons/utils/i18n';

const App: FC = () => {
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        setup();
        setLoading(false);
    }, []);

    return loading ? (
        <div>Loading</div>
    ) : (
        <Provider store={store}>
            <AppContainer />
        </Provider>
    );
};

const AppContainer = (): React.ReactElement => {
    const { lang } = useAppSelector((state) => state.app);

    return (
        <RawIntlProvider value={createReactIntl(lang)}>
            <IonApp>
                <IonReactRouter history={history}>
                    <IonRouterOutlet id="main">
                        <Route path="/tabs" render={() => <Main />} />
                        <Route path="/login" component={Login} exact={true} />
                        <Redirect exact path="/" to="/tabs" />
                    </IonRouterOutlet>
                </IonReactRouter>
            </IonApp>
        </RawIntlProvider>
    );
};
export default App;
