package cn.elvea.platform.core.i18n.service;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * I18NServiceTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class I18NServiceTests extends BaseTests {

    @Autowired
    private I18NService i18NService;

    @Test
    public void generateI18nTest() {
        this.i18NService.generateI18NFile();
    }

    @Test
    public void translateI18nTest() {
        this.i18NService.translate();
    }

    @Test
    public void exportI18nTest() {
        this.i18NService.exportExcel();
    }

    @Test
    public void importI18nTest() {
        this.i18NService.importExcel();
    }

}
