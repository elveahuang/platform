import React, { useEffect, useState, Suspense } from 'react';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import { RawIntlProvider, useIntl } from 'react-intl';
import { ConfigProvider, Spin } from 'antd';
import { Provider } from 'react-redux';
import { useMount } from 'ahooks';
//
import './App.scss';
import store from '@commons/store';
import { useAppSelector } from '@commons/hooks';
import { createReactIntl } from '@commons/utils/i18n';
import { antdLocalProvider } from '@commons/webapp/utils/antd';
import { AdminLayout, MainLayout } from '@/layouts';
import { Loading } from '@commons/webapp/components';
import { setup } from '@/utils';
import { aesDecrypt, aesEncrypt, decrypt, encrypt } from '@commons/utils/encrypt';
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

    /**
     * 顶层组件实现初始化
     */
    useMount(() => {
        const encrypted = encrypt('root');
        console.log(`encrypted - ${encrypted}`);
        const decrypted = decrypt(encrypted);
        console.log(`decrypted - ${decrypted}`);
        const aesEncrypted = aesEncrypt('root');
        console.log(`aesEncrypted - ${aesEncrypted}`);
        const aesDecrypted = aesDecrypt(aesEncrypted);
        console.log(`aesDecrypted - ${aesDecrypted}`);
        setup();
    });

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
        </RawIntlProvider>
    );
};
export default App;
