package cn.elvea.platform.commons.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class ObjectUtils extends org.springframework.util.ObjectUtils {

    /**
     * 如果第一个参数为空，则返回第二个参数
     */
    public static <T> T nvl(final T object, final T defaultObject) {
        return isEmpty(object) ? defaultObject : object;
    }

    /**
     *
     */
    public static boolean equals(Object o1, Object o2) {
        if (o1 instanceof Number n1 && o2 instanceof Number n2) {
            return n1.longValue() == n2.longValue();
        }
        return nullSafeEquals(o1, o2);
    }

    public static void copyNotNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    public static String[] getNullProperties(Object object) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> fieldSet = new HashSet<>();
        for (PropertyDescriptor p : propertyDescriptors) {
            String propertyName = p.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            if (Objects.isNull(propertyValue)) {
                fieldSet.add(propertyName);
            }
        }

        return fieldSet.toArray(new String[0]);
    }

}
