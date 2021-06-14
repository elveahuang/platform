import React, { Suspense, useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import { IntlProvider, useIntl } from 'react-intl';
import { ConfigProvider, Spin } from 'antd';
import { Provider } from 'react-redux';
//
import store from '@/store';
import { useAppSelector } from '@/hooks';
import { localeMessages } from '@commons/utils/i18n';
import { antdLocalProvider } from '@commons/webapp/utils/antd';
import { AdminLayout, MainLayout } from '@/layouts';
//
import './App.scss';
import { Loading } from '@commons/webapp/components';
//
const Login = React.lazy(() => import('@/pages/Login'));

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

    return (
        <IntlProvider messages={localeMessages[state.app.lang]} locale={state.app.lang}>
            <ConfigProvider direction={state.app.direction} locale={antdLocalProvider[state.app.lang]}>
                <Router>
                    <React.Fragment>
                        <AppTitle />
                        <Suspense fallback={<Loading />}>
                            <Switch>
                                <Route exact path="/login" component={Login} />
                                <Route path="/admin" component={AdminLayout} />
                                <Route path="/" component={MainLayout} />
                            </Switch>
                        </Suspense>
                    </React.Fragment>
                </Router>
            </ConfigProvider>
        </IntlProvider>
    );
};
export default App;
