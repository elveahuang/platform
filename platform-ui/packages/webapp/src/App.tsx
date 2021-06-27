import React, { Suspense, useEffect, useState } from 'react';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import { RawIntlProvider, useIntl } from 'react-intl';
import { ConfigProvider } from 'antd';
import { Provider } from 'react-redux';
import { SWRConfig } from 'swr';
//
import './App.scss';
import store from '@commons/store';
import { CommonService } from '@commons/services';
import { useAppSelector } from '@commons/hooks';
import { createReactIntl } from '@commons/utils/i18n';
import { antdLocalProvider } from '@commons/webapp/utils/antd';
import { AdminLayout, MainLayout } from '@/layouts';
import { Loading } from '@commons/webapp/components';
import { setup } from '@/utils';
import { swrConfig } from '@commons/utils/swr';
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
        CommonService.initialize()
            .then(() => {
                setup();
                setLoading(false);
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    return loading ? (
        <Loading />
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
                <SWRConfig value={swrConfig}>
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
                </SWRConfig>
            </ConfigProvider>
        </RawIntlProvider>
    );
};
export default App;
