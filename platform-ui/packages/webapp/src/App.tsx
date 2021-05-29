import React, { Suspense, useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import { IntlProvider, useIntl } from 'react-intl';
import { ConfigProvider, Spin } from 'antd';
//
import { applicationLocaleMessages } from '@commons/constants';
import { applicationLocalProdiver } from '@/core/app-constants';
import {
    ApplicationProvider,
    ApplicationState,
    defaultApplicationState,
    initialize,
    useApplicationContext,
} from '@/context/Application';
//
import './App.less';
//
const Dashboard = React.lazy(() => import('@/pages/Admin/Dashboard'));
const AboutUs = React.lazy(() => import('@/pages/AboutUs'));
const Login = React.lazy(() => import('@/pages/Login'));
const Home = React.lazy(() => import('@/pages/Home'));

const AppTitle = (): React.ReactElement => {
    const intl = useIntl();
    return (
        <Helmet
            title={intl.formatMessage({
                id: 'site_title',
            })}
        />
    );
};

const App = (): React.ReactElement => {
    const [loading, setLoading] = useState(true);
    const [state, setState] = useState<ApplicationState>(defaultApplicationState);

    useEffect(() => {
        initialize().then((state: ApplicationState) => {
            setState(state);
            setLoading(false);
        });
    }, []);

    return loading ? (
        <Spin />
    ) : (
        <ApplicationProvider state={state}>
            <AppContainer />
        </ApplicationProvider>
    );
};

const AppContainer = (): React.ReactElement => {
    const { state } = useApplicationContext();

    return (
        <IntlProvider messages={applicationLocaleMessages[state.preference.locale]} locale={state.preference.locale}>
            <ConfigProvider locale={applicationLocalProdiver[state.preference.locale]}>
                <Router>
                    <React.Fragment>
                        <AppTitle />
                        <Suspense fallback={<Spin />}>
                            <Switch>
                                <Route exact path="/admin" component={Dashboard} />
                                <Route exact path="/" component={Home} />
                                <Route exact path="/login" component={Login} />
                                <Route exact path="/about-us" component={AboutUs} />
                            </Switch>
                        </Suspense>
                    </React.Fragment>
                </Router>
            </ConfigProvider>
        </IntlProvider>
    );
};
export default App;
