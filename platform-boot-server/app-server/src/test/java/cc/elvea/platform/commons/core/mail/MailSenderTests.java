package cc.elvea.platform.commons.core.mail;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.autoconfigure.core.properties.TestProperties;
import cc.elvea.platform.commons.core.mail.model.MailBody;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class MailSenderTests extends BaseTests {

    @Autowired
    private TestProperties properties;

    @Autowired
    private MailFactory mailFactory;

    @Test
    public void base() throws Exception {
        MailBody body = MailBody.builder()
                .subject("Test")
                .content("Java Mail Sender.")
                .to(this.properties.getEmail())
                .build();
        mailFactory.getMailSender().send(body);
    }

}