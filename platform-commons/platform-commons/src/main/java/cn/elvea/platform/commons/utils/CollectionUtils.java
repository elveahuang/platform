package cn.elvea.platform.commons.utils;

import com.google.common.collect.Lists;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CollectionUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class CollectionUtils extends org.springframework.util.CollectionUtils {

    /**
     * 是否不为空
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 是否不为空
     */
    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 移除重复元素
     */
    public static <E> List<E> removeDuplicates(List<E> list) {
        if (isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

}
