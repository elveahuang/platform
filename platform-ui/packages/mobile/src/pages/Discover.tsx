import React, { FC } from 'react';
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';

const Discover: FC = () => {
    return (
        <IonPage>
            <IonHeader>
                <IonToolbar>
                    <IonTitle>Discover</IonTitle>
                </IonToolbar>
            </IonHeader>
            <IonContent>
                <IonTitle>Discover</IonTitle>
            </IonContent>
        </IonPage>
    );
};

export default Discover;
