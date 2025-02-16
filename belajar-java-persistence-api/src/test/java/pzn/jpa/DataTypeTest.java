package pzn.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import pzn.jpa.entities.Customer;
import pzn.jpa.util.JpaUtil;

public class DataTypeTest {

  @Test
  void dataType() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    entityTransaction.begin();

    Customer customer = new Customer();
    customer.setId("2");
    customer.setName("Budi");
    customer.setAge((byte) 20);
    customer.setMarried(true);
    customer.setPrimaryEmail("contoh1@example.com");

    entityManager.persist(customer);

    entityTransaction.commit();
    entityManager.close();
  }
}
