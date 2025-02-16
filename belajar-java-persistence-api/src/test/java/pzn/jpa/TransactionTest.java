package pzn.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pzn.jpa.util.JpaUtil;

public class TransactionTest {

  @Test
  void transaction() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    Assertions.assertNotNull(entityTransaction);

    try {
      entityTransaction.begin();
      // DO SOMETHING
      entityTransaction.commit();
    } catch (Throwable e) {
      entityTransaction.rollback();
    }


    entityManager.close();
    entityManagerFactory.close();
  }
}
