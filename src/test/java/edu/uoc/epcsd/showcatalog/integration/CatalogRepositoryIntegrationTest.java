package edu.uoc.epcsd.showcatalog.integration;

import edu.uoc.epcsd.showcatalog.domain.Category;
import edu.uoc.epcsd.showcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ComponentScan(basePackages = "edu.uoc.epcsd.showcatalog.infrastructure.repository.jpa")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CatalogRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void whenFindCategoryById_thenReturnCategory() {
        CategoryEntity entity = new CategoryEntity();
        entity.setName("Music");

        testEntityManager.persistAndFlush(entity);

        Optional<Category> category = categoryRepository.findCategoryById(entity.getId());
        assertThat(category.isPresent()).isTrue();
        assertThat(category.get().getId()).isEqualTo(entity.getId());
        assertThat(category.get().getName()).isEqualTo(entity.getName());
    }

}
