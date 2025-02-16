package pzn.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import pzn.jpa.entities.Customer;
import pzn.jpa.entities.CustomerType;
import pzn.jpa.util.JpaUtil;

public class EnumTest {

  @Test
  void enumTest() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    entityTransaction.begin();

    Customer customer = new Customer();
    customer.setId("3");
    customer.setName("Eko");
    customer.setAge((byte) 23);
    customer.setMarried(false);
    customer.setType(CustomerType.PREMIUM);
    customer.setPrimaryEmail("contoh2@example.com");

    entityManager.persist(customer);

    entityTransaction.commit();
    entityManager.close();
  }
}
