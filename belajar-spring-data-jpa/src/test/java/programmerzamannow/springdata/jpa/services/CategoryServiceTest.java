package programmerzamannow.springdata.jpa.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void success() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.create();
        });
    }

    @Test
    void programmatic() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.createCategories();
        });
    }

    @Test
    void manual() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.createCategories();
        });
    }

    @Test
    void failed() {
//        cek db data seharusnya tidak masuk tetapi jadi masuk
        assertThrows(RuntimeException.class, () -> {
            categoryService.test();
        });
    }

}