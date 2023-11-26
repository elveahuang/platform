package cn.elvea.platform.commons.core.extensions.keyword;

import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.hutool.dfa.WordTree;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class DefaultKeywordManager implements KeywordManager {

    private final WordTree tree = new WordTree();

    /**
     * @see KeywordManager#initialize(List)
     */
    @Override
    public void initialize(List<String> list) {
        tree.clear();
        tree.addWords(list);
    }

    /**
     * @see KeywordManager#check(String)
     */
    @Override
    public boolean check(String text) {
        return StringUtils.isNotEmpty(tree.match(text));
    }

}
