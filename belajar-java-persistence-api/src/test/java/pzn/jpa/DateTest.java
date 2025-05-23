package pzn.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pzn.jpa.entities.Category;
import pzn.jpa.util.JpaUtil;

import java.time.LocalDateTime;
import java.util.Calendar;

public class DateTest {

  @Test
  void dateTest() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    entityTransaction.begin();

    Category category = new Category();
    category.setName("Elektronik");
    category.setDescription("Kategori produk elektronik");
    category.setCreatedAt(Calendar.getInstance());
    category.setUpdatedAt(LocalDateTime.now());
    entityManager.persist(category);

    Assertions.assertNotNull(category.getId());

    entityTransaction.commit();
    entityManager.close();
  }
}
