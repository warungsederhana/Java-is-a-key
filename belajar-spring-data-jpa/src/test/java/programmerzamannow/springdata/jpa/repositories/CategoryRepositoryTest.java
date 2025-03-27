package programmerzamannow.springdata.jpa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import programmerzamannow.springdata.jpa.entities.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insert() {
        Category category = new Category();
        category.setName("Gadget");

        categoryRepository.save(category);

        assertNotNull(category.getId());
    }

    @Test
    void update() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        category.setName("Gadget Murah");
        categoryRepository.save(category);

        category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);
        assertEquals("Gadget Murah", category.getName());
    }

    @Test
    void testQueryMethod() {
        Category category = categoryRepository.findFirstByNameEquals("Gadget Murah").orElse(null);
        assertNotNull(category);
        assertEquals("Gadget Murah", category.getName());

        List<Category> categories = categoryRepository.findAllByNameLike("%Gadget%");
        assertEquals(4, categories.size());
        assertEquals("Gadget Murah", categories.get(0).getName());
    }
}