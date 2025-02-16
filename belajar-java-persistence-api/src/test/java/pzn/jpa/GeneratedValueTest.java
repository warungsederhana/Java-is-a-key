package pzn.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pzn.jpa.entities.Category;
import pzn.jpa.entities.Customer;
import pzn.jpa.util.JpaUtil;

public class GeneratedValueTest {
  @Test
  void generatedValue() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    entityTransaction.begin();

    Category category = new Category();
    category.setName("Elektronik");
    category.setDescription("Kategori produk elektronik");
    entityManager.persist(category);

    Assertions.assertNotNull(category.getId());

    entityTransaction.commit();
    entityManager.close();
  }
}
