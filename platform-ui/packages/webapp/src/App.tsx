import React, { Suspense, useEffect, useState } from 'react';
import { BrowserRouter as Router, Redirect, Route, Switch } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import { IntlProvider, useIntl } from 'react-intl';
import { ConfigProvider, Spin } from 'antd';
//
//
import './App.less';
import { Provider } from 'react-redux';
import store from '@/store';
import { useAppDispatch, useAppSelector } from '@/hooks';
import { applicationLocaleMessages } from '@commons/utils/i18n';
import { antdLocalProvider } from '@commons/utils/antd';
import validator from 'validator';
import isEmpty = validator.isEmpty;
import UserService from '@commons/services/UserService';
import { AxiosResponse } from 'axios';
import Principal from '@commons/types/principal';
import { initialPreference } from '@commons/types/preference';
import { setAccessToken, setRefreshToken, setUser } from '@commons/store/user';
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

    useEffect(() => {
        setLoading(false);
    }, []);

    return loading ? (
        <Spin />
    ) : (
        <Provider store={store}>
            <AppContainer />
        </Provider>
    );
};

const AppContainer = (): React.ReactElement => {
    const state = useAppSelector((state) => state);
    const dispatch = useAppDispatch();
    const { authenticated, accessToken } = useAppSelector((state) => state.user);

    return (
        <IntlProvider messages={applicationLocaleMessages[state.app.lang]} locale={state.app.lang}>
            <ConfigProvider locale={antdLocalProvider[state.app.lang]}>
                <Router>
                    <React.Fragment>
                        <AppTitle />
                        <Suspense fallback={<Spin />}>
                            <Switch>
                                <Route exact path="/" component={Home} />
                                <Route exact path="/login" component={Login} />
                                <Route exact path="/about-us" component={AboutUs} />
                                <Route
                                    path="/admin"
                                    render={({ history }) => {
                                        if (isEmpty(accessToken)) {
                                            if (authenticated) {
                                                return <Dashboard />;
                                            } else {
                                                UserService.user()
                                                    .then(async (resp: AxiosResponse<Principal>) => {
                                                        dispatch(setUser(resp.data));
                                                    })
                                                    .catch(() => {
                                                        history.replace('/login');
                                                    });
                                                return <Spin />;
                                            }
                                        } else {
                                            return <Redirect to="/index" />;
                                        }
                                    }}
                                />
                                <Route exact path="/admin" component={Dashboard} />
                            </Switch>
                        </Suspense>
                    </React.Fragment>
                </Router>
            </ConfigProvider>
        </IntlProvider>
    );
};
export default App;
