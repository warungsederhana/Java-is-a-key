package programmerzamannow.springdata.jpa.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.support.TransactionOperations;
import programmerzamannow.springdata.jpa.entities.Category;
import programmerzamannow.springdata.jpa.entities.Product;
import programmerzamannow.springdata.jpa.models.ProductPrice;
import programmerzamannow.springdata.jpa.models.SimpleProduct;

import java.util.List;
import java.util.stream.Stream;

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

    // named query test
    @Test
    void searchProductUsingName() {
        List<Product> products = productRepository.searchProductUsingName("Apple iPhone 14 Pro Max");
        assertEquals(1, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
    }

    // named query test
    @Test
    void searchProductUsingNamePageable() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Product> products = productRepository.searchProductUsingName("Apple iPhone 14 Pro Max", pageable);
        assertEquals(1, products.size());
        assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
    }

    // query annotation test
    @Test
    void searchProduct() {
        List<Product> products = productRepository.searchProduct("%iPhone%");
        assertEquals(2, products.size());

        products = productRepository.searchProduct("%Gadget%");
        assertEquals(2, products.size());
    }

    // query annotation test with pageable and sort
    @Test
    void searchProductPageableSort() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.searchProduct("%iPhone%", pageable);
        assertEquals(1, products.getContent().size());

        assertEquals(0, products.getNumber());
        assertEquals(2, products.getTotalPages());
        assertEquals(2, products.getTotalElements());

        products = productRepository.searchProduct("%Gadget%", pageable);
        assertEquals(1, products.getContent().size());

        assertEquals(0, products.getNumber());
        assertEquals(2, products.getTotalPages());
        assertEquals(2, products.getTotalElements());
    }

    @Test
    void modifying() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            int total = productRepository.deleteProductUsingName("Wrong");
            assertEquals(0, total);

            total = productRepository.updateProductPriceToZero(1L);
            assertEquals(1, total);

            Product product = productRepository.findById(1L).orElse(null);
            assertNotNull(product);
            assertEquals(0L, product.getPrice());
        });
    }

    @Test
    void streamAllByCategory() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            assertNotNull(category);

            Stream<Product> products = productRepository.streamAllByCategory(category);
            products.forEach(product -> System.out.println(product.getId() + " : " + product.getName() ));
        });
    }

    @Test
    void findAllByCategorySlice() {
      Pageable firstPage = PageRequest.of(0, 1);
      Category category = categoryRepository.findById(1L).orElse(null);
      assertNotNull(category);

      Slice<Product> slice = productRepository.findAllByCategory(category, firstPage);
      // tampilkan content product
      while(slice.hasNext()) {
        slice = productRepository.findAllByCategory(category, slice.nextPageable());
        // tampilkan content product
      }
    }

    @Test
    void lock1() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            try {
                Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
                assertNotNull(product);
                product.setPrice(30_000_000L);

                Thread.sleep(20_000L);
                productRepository.save(product);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }

        });
    }

    @Test
    void lock2() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
            assertNotNull(product);
            product.setPrice(10_000_000L);
            productRepository.save(product);

        });
    }

    @Test
    void specification() {
        Specification<Product> specification = (root, citeria, builder) -> {
            return citeria.where(
                builder.or(
                    builder.equal(root.get("name"), "Apple iPhone 14 Pro Max"),
                    builder.equal(root.get("name"), "Apple iPhone 13 Pro Max")
                )
            ).getRestriction();
        };

        List<Product> products = productRepository.findAll(specification);
        assertEquals(2, products.size());
    }

    @Test
    void projection() {
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%Apple%", SimpleProduct.class);
        assertEquals(2, simpleProducts.size());

        List<ProductPrice> productPrices = productRepository.findAllByNameLike("%Apple%", ProductPrice.class);
        assertEquals(2, productPrices.size());
    }
}