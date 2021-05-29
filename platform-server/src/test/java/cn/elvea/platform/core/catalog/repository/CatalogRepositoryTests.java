package cn.elvea.platform.core.catalog.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.core.catalog.domain.entity.CatalogEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

/**
 * CatalogRepositoryTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class CatalogRepositoryTests extends BaseTests {

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogRepositoryTests(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Test
    public void test() {
        CatalogEntity entity = new CatalogEntity();
        entity.setCode("001");
        entity.setActive(true);
        this.catalogRepository.save(entity);
        Assertions.assertNotNull(this.catalogRepository);

        Optional<CatalogEntity> catalog = this.catalogRepository.findById(entity.getId());
        Assertions.assertTrue(catalog.isPresent());

        Page<CatalogEntity> catalogEntityList = this.catalogRepository.findAll(PageRequest.of(1, 1));
        Assertions.assertNotNull(catalogEntityList.getContent());
    }

}
