import React, { FC } from 'react';
import { Button, Card, Col, Form, Input, Row } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import { FormattedMessage, useIntl } from 'react-intl';
import { Store, ValidateErrorEntity } from 'rc-field-form/lib/interface';
import { useHistory } from 'react-router';
import { AxiosResponse } from 'axios';
//
import './Login.scss';
import { applicationVersion } from '@commons/constants';
import { UserService } from '@commons/services';
import DefaultLayout from '@/layouts/DefaultLayout';
import { OAuthTokenResult } from '@commons/types/oauth/oauth-token-result';
import Principal from '@commons/types/principal';
import { useAppDispatch } from '@commons/hooks';
import { setAccessToken, setRefreshToken, setUser } from '@commons/store/user';

const Login: FC = () => {
    const intl = useIntl();
    const history = useHistory();
    const dispatch = useAppDispatch();

    const onFinish = (values: Store) => {
        UserService.auth({
            grant_type: 'password',
            client_id: 'webapp',
            client_secret: 'webapp',
            username: values.username,
            password: values.password,
            clientVersion: applicationVersion,
        }).then(async (resp: AxiosResponse<OAuthTokenResult>) => {
            dispatch(setAccessToken(resp.data.access_token));
            dispatch(setRefreshToken(resp.data.refresh_token));
            UserService.user().then(async (resp: AxiosResponse<Principal>) => {
                dispatch(setUser(resp.data));
                history.push('/admin');
            });
        });
    };

    const onFinishFailed = (e: ValidateErrorEntity) => {
        console.log(e);
    };

    return (
        <DefaultLayout layoutClassName="login-page-layout">
            <Row justify="center" align="middle" className={'login-container'}>
                <Col span={6}>
                    <Card className={'login-card'} title={intl.formatMessage({ id: 'user_page_login_title' })}>
                        <Form className={'login-form'} onFinish={onFinish} onFinishFailed={onFinishFailed}>
                            <Form.Item
                                name="username"
                                initialValue="admin"
                                rules={[{ required: true, message: 'Please input your Username!' }]}
                            >
                                <Input
                                    prefix={<UserOutlined />}
                                    type="text"
                                    size="large"
                                    placeholder={intl.formatMessage({ id: 'user_field_username' })}
                                />
                            </Form.Item>
                            <Form.Item
                                name="password"
                                initialValue="admin"
                                rules={[{ required: true, message: 'Please input your Password!' }]}
                            >
                                <Input.Password
                                    size="large"
                                    prefix={<UserOutlined />}
                                    placeholder={intl.formatMessage({ id: 'user_field_password' })}
                                />
                            </Form.Item>
                            <Form.Item>
                                <Button block type="primary" htmlType="submit">
                                    <FormattedMessage id={'button_submit'} />
                                </Button>
                            </Form.Item>
                        </Form>
                    </Card>
                </Col>
            </Row>
        </DefaultLayout>
    );
};

export default Login;
