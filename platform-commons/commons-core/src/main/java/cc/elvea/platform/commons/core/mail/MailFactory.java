package cc.elvea.platform.commons.core.mail;

import cc.elvea.platform.commons.core.mail.spring.SpringMailSender;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public record MailFactory(MailConfig config) {

    public MailSender getMailSender() {
        return getMailSender(this.config);
    }

    public MailSender getMailSender(MailConfig config) {
        return new SpringMailSender(config);
    }

}
