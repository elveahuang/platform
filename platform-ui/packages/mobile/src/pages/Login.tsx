import React, { FC } from 'react';
import {
    IonButton,
    IonCol,
    IonContent,
    IonHeader,
    IonInput,
    IonItem,
    IonLabel,
    IonList,
    IonPage,
    IonRow,
    IonTitle,
    IonToolbar,
} from '@ionic/react';
import { FormattedMessage, useIntl } from 'react-intl';

const Login: FC = () => {
    const intl = useIntl();

    const handleLoginSubmit = (e: React.FormEvent) => {};

    return (
        <IonPage>
            <IonHeader>
                <IonToolbar>
                    <IonTitle>{intl.formatMessage({ id: 'user_page_login_title' })}</IonTitle>
                </IonToolbar>
            </IonHeader>
            <IonContent>
                <form noValidate onSubmit={handleLoginSubmit}>
                    <IonList>
                        <IonItem>
                            <IonLabel position="stacked" color="primary">
                                {intl.formatMessage({ id: 'user_field_username' })}
                            </IonLabel>
                            <IonInput name="username" type="text" required />
                        </IonItem>

                        <IonItem>
                            <IonLabel position="stacked" color="primary">
                                {intl.formatMessage({ id: 'user_field_password' })}
                            </IonLabel>
                            <IonInput name="password" type="password" />
                        </IonItem>
                    </IonList>

                    <IonRow>
                        <IonCol>
                            <IonButton type="submit" expand="block">
                                <FormattedMessage id={'button_submit'} />
                            </IonButton>
                        </IonCol>
                    </IonRow>
                </form>
            </IonContent>
        </IonPage>
    );
};

export default Login;
