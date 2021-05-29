package cn.elvea.platform.commons.autoconfigure.translater;

import cn.elvea.platform.commons.translater.TranslatorConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TranslatorConfigProperties
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("platform.translator")
public class TranslatorConfigProperties extends TranslatorConfig {
}
