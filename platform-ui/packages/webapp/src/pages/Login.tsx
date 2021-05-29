import React from 'react';
import DefaultLayout from '../components/Layouts/DefaultLayout';
//
import './Login.less';
import { Button, Card, Col, Form, Input, Row } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import { FormattedMessage, useIntl } from 'react-intl';
import { Store, ValidateErrorEntity } from 'rc-field-form/lib/interface';
import { applicationVersion } from '@commons/constants';
import { UserService } from '@commons/services';
import { ApplicationContextActionType, useApplicationContext } from '@/context/Application';
import { useHistory } from 'react-router';
import { OAuthTokenResult } from '@commons/types/oauth/oauth-token-result';
import { AxiosResponse } from 'axios';
import { Principal } from '@commons/types/principal';

const Login = (): React.ReactElement => {
    const intl = useIntl();
    const history = useHistory();
    const { dispatch } = useApplicationContext();

    const onFinish = (values: Store) => {
        UserService.auth({
            grant_type: 'password',
            client_id: 'webapp',
            client_secret: 'webapp',
            username: values.username,
            password: values.password,
            clientVersion: applicationVersion,
        }).then(async (resp: AxiosResponse<OAuthTokenResult>) => {
            dispatch({
                type: ApplicationContextActionType.LOGIN,
                access_token: resp.data.access_token,
                refresh_token: resp.data.refresh_token,
            });
            UserService.user().then(async (resp: AxiosResponse<Principal>) => {
                dispatch({
                    type: ApplicationContextActionType.PRINCIPAL,
                    principal: resp.data,
                });
                history.push('/admin');
            });
        });
    };

    const onFinishFailed = (e: ValidateErrorEntity) => {
        console.log(e);
    };

    return (
        <DefaultLayout>
            <div>
                <Row justify="center" align="middle">
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
            </div>
        </DefaultLayout>
    );
};

export default Login;
