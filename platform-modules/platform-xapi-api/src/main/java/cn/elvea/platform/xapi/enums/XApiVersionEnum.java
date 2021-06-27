package cn.elvea.platform.xapi.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 版本枚举
 *
 * @author elvea
 */
@Getter
public enum XApiVersionEnum {
    V101("1.0.1"),
    V102("1.0.2"),
    V103("1.0.3");

    private final String text;

    XApiVersionEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    /**
     * 获取支持的最新版本
     *
     * @return {{@link XApiVersionEnum}}
     */
    public static XApiVersionEnum latest() {
        return XApiVersionEnum.V103;
    }

    /**
     * 获取所有支持版本
     */
    public static List<String> versions() {
        List<String> versionList = new ArrayList<>();
        for (XApiVersionEnum v : XApiVersionEnum.values()) {
            versionList.add(v.text);
        }
        return versionList;
    }

    /**
     * 获取对应的版本，找不到对应的版本时，将返回支持的最新版本
     *
     * @return {{@link XApiVersionEnum}}
     */
    public static XApiVersionEnum fromString(String text) {
        for (XApiVersionEnum v : XApiVersionEnum.values()) {
            if (v.text.equalsIgnoreCase(text)) {
                return v;
            }
        }
        return XApiVersionEnum.latest();
    }

}
