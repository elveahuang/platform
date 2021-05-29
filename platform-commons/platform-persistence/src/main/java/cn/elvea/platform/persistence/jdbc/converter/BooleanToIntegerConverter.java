package cn.elvea.platform.persistence.jdbc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * BooleanToIntegerConverter
 *
 * @author elvea
 * @since 0.0.1
 */
@WritingConverter
public class BooleanToIntegerConverter implements Converter<Boolean, Integer> {

    /**
     * @see Converter#convert(Object)
     */
    @Override
    public Integer convert(Boolean source) {
        return source != null && Boolean.TRUE.equals(source) ? 1 : 0;
    }

}
