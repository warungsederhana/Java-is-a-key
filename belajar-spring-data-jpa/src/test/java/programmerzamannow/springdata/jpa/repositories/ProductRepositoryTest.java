package programmerzamannow.springdata.jpa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.support.TransactionOperations;
import programmerzamannow.springdata.jpa.entities.Category;
import programmerzamannow.springdata.jpa.entities.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;


    @Test
    void createProducts() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Apple iPhone 14 Pro Max");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

        {
            Product product = new Product();
            product.setName("Apple iPhone 13 Pro Max");
            product.setPrice(18_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }
    }

    @Test
    void findByCategoryName() {
        List<Product> products = productRepository.findAllByCategory_Name("Gadget Murah");
        assertEquals(2, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
        assertEquals("Apple iPhone 13 Pro Max", products.get(1).getName());
    }

    @Test
    void findByCategoryNameSortDescId() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Product> products = productRepository.findAllByCategory_Name("Gadget Murah", sort);

        assertEquals(2, products.size());
        assertEquals("Apple iPhone 13 Pro Max", products.get(0).getName());
        assertEquals("Apple iPhone 14 Pro Max", products.get(1).getName());
    }

    @Test
    void findByCategoryNamePageable() {
        // default dimulai dari 0
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.findAllByCategory_Name("Gadget Murah", pageable);

        assertEquals(1, products.getContent().size());
        assertEquals(0, products.getNumber());
        assertEquals(2, products.getTotalElements());
        assertEquals(2, products.getTotalPages());
        assertEquals("Apple iPhone 13 Pro Max", products.getContent().get(0).getName());

        // page 1
        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("Gadget Murah", pageable);

        assertEquals(1, products.getContent().size());
        assertEquals(1, products.getNumber());
        assertEquals(2, products.getTotalElements());
        assertEquals(2, products.getTotalPages());
        assertEquals("Apple iPhone 14 Pro Max", products.getContent().get(0).getName());
    }

    @Test
    void countByCategoryName() {
        Long count = productRepository.count();
        assertEquals(2L, count);

        count = productRepository.countByCategory_Name("Gadget Murah");
        assertEquals(2L, count);

        count = productRepository.countByCategory_Name("Gada");
        assertEquals(0L, count);
    }

    @Test
    void existsByName() {
        boolean exists = productRepository.existsByName("Apple iPhone 14 Pro Max");
        assertTrue(exists);

        exists = productRepository.existsByName("Apple iPhone 16 Pro Max");
        assertFalse(exists);
    }

    @Test
    void deleteByName() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);


        Product product = new Product();
        product.setName("Samsung Galaxy S9");
        product.setPrice(15_000_000L);
        product.setCategory(category);
        productRepository.save(product);

        int delete = productRepository.deleteByName("Samsung Galaxy S9");
        assertEquals(1, delete);

        // test not exists
        delete = productRepository.deleteByName("Samsung Galaxy S9");
        assertEquals(0, delete);
    }

    @Test
    void searchProductUsingName() {
        List<Product> products = productRepository.searchProductUsingName("Apple iPhone 14 Pro Max");
        assertEquals(1, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
    }
}