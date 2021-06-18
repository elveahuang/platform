import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import { RawIntlProvider, useIntl } from 'react-intl';
import { ConfigProvider, Spin } from 'antd';
import { Provider } from 'react-redux';
//
import './App.scss';
import store from '@commons/store';
import { useAppSelector } from '@commons/hooks';
import { createReactIntl } from '@commons/utils/i18n';
import { antdLocalProvider } from '@commons/webapp/utils/antd';
import { AdminLayout, MainLayout } from '@/layouts';
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
    const { lang, direction } = useAppSelector((state) => state.app);

    return (
        <RawIntlProvider value={createReactIntl(lang)}>
            <ConfigProvider direction={direction} locale={antdLocalProvider[lang]}>
                <Router>
                    <React.Fragment>
                        <AppTitle />
                        <Switch>
                            <Route exact path="/login" component={Login} />
                            <Route path="/admin" component={AdminLayout} />
                            <Route path="/" component={MainLayout} />
                        </Switch>
                    </React.Fragment>
                </Router>
            </ConfigProvider>
        </RawIntlProvider>
    );
};
export default App;
