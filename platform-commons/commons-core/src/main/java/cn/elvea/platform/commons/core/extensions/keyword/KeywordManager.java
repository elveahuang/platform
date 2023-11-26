package cn.elvea.platform.commons.core.extensions.keyword;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface KeywordManager {

    void initialize(List<String> list);

    boolean check(String text);

}
