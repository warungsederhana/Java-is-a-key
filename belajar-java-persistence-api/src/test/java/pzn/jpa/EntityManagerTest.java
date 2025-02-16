package pzn.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pzn.jpa.util.JpaUtil;

public class EntityManagerTest {

  @Test
  void create() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    Assertions.assertNotNull(entityManager);
    // database operation

    entityManager.close();
  }
}
