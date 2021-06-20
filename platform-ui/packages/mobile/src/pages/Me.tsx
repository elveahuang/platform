import React, { FC } from 'react';
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';

const Me: FC = () => {
    return (
        <IonPage>
            <IonHeader>
                <IonToolbar>
                    <IonTitle>Me</IonTitle>
                </IonToolbar>
            </IonHeader>
            <IonContent>
                <IonTitle>Me</IonTitle>
            </IonContent>
        </IonPage>
    );
};

export default Me;
