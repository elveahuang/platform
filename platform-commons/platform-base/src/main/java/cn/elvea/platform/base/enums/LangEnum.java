package cn.elvea.platform.base.enums;

import lombok.Getter;

import java.util.Locale;

/**
 * 时区枚举
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum LangEnum implements BaseEnum<String> {
    ZH_CN("zh_CN", "label_lang_zh_cn", "简体中文", Locale.SIMPLIFIED_CHINESE),
    ZH_TW("zh_TW", "label_lang_zh_tw", "繁体中文", Locale.TRADITIONAL_CHINESE),
    EN_US("en_US", "label_lang_en_us", "美式英文", Locale.US);

    private final String code;
    private final String label;
    private final String description;
    private final Locale locale;

    LangEnum(final String code, final String label, final String description, final Locale locale) {
        this.code = code;
        this.label = label;
        this.description = description;
        this.locale = locale;
    }

    public static LangEnum getLang(String code) {
        LangEnum[] ts = LangEnum.values();
        for (LangEnum t : ts) {
            if (t.getCode().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return LangEnum.ZH_CN;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

}
