package cn.elvea.platform.core.catalog.converter;

import cn.elvea.platform.core.catalog.domain.entity.CatalogTypeEntity;
import cn.elvea.platform.core.catalog.domain.mapper.CatalogTypeConverter;
import cn.elvea.platform.core.catalog.dto.CatalogTypeDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CatalogTypeConverterTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class CatalogTypeConverterTests {

    @Test
    public void test() {
        CatalogTypeEntity entity = new CatalogTypeEntity();
        entity.setId(1L);
        entity.setCode("CAT001");
        CatalogTypeDto dto = CatalogTypeConverter.INSTANCE.entityToDto(entity);
        assertNotNull(dto);
    }

}
