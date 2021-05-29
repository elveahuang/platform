package cn.elvea.platform.core.i18n.service;

/**
 * 国际化多语言服务
 *
 * @author elvea
 * @since 0.0.1
 */
public interface I18NService {

    /**
     * 生成多语言国际化文件，仅限开发环境下使用。
     */
    void generateI18NFile();

    /**
     * 翻译多语言文本，仅限开发环境下使用。
     */
    void translate();

    /**
     * 导出多语言文本，仅限开发环境下使用。
     */
    void exportExcel();

    /**
     * 导入多语言文本，仅限开发环境下使用。
     */
    void importExcel();

}
