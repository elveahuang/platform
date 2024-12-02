package cc.elvea.platform.commons.oapis.sms;

import cc.elvea.platform.commons.oapis.sms.model.SmsBody;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface SmsSender<C> {

    void send(SmsBody body) throws Exception;

    void send(C config, SmsBody body) throws Exception;

}
