package pzn.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import pzn.jpa.entities.Customer;
import pzn.jpa.util.JpaUtil;

public class ColumnTest {

  @Test
  void column() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    entityTransaction.begin();

    Customer customer = new Customer();
    customer.setId("1");
    customer.setName("Gybran");
    customer.setPrimaryEmail("contoh@example.com");

    entityManager.persist(customer);

    entityTransaction.commit();
    entityManager.close();
  }
}
