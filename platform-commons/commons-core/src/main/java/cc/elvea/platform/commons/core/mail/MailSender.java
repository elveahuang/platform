package cc.elvea.platform.commons.core.mail;

import cc.elvea.platform.commons.core.mail.model.MailBody;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface MailSender {

    void send(MailBody body) throws Exception;

}
