import React from 'react';
import { useIntl } from 'react-intl';
import { Layout } from 'antd';
import classNames from 'classnames';
//
//
import './DefaultLayout.less';

type DefaultLayoutProps = {
    layoutClassName?: string;
    children?: React.ReactNode;
};

const DefaultLayout = (props: DefaultLayoutProps): React.ReactElement => {
    const intl = useIntl();
    const layoutClassName = classNames('default-layout', props.layoutClassName || '');

    return (
        <Layout className={layoutClassName}>
            <Layout.Content>{props.children}</Layout.Content>
            <Layout.Footer id="footer">
                {intl.formatMessage(
                    {
                        id: 'site_copyright',
                    },
                    {
                        curDate: new Date(),
                    },
                )}
            </Layout.Footer>
        </Layout>
    );
};

export default DefaultLayout;
