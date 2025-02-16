package pzn.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pzn.jpa.entities.Customer;
import pzn.jpa.util.JpaUtil;

public class CrudTest {

  private EntityManagerFactory entityManagerFactory;

  @BeforeEach
  void setUp() {
    entityManagerFactory = JpaUtil.getEntityManagerFactory();
  }

  @Test
  @Disabled
  void insert() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    Customer customer = new Customer();
    customer.setId("1");
    customer.setName("John");

    // memasukkan data ke dalam database
    entityManager.persist(customer);

    entityTransaction.commit();
    entityManager.close();
  }

  @Test
  @Disabled
  void find() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    // mencari data berdasarkan primary key
    Customer customer = entityManager.find(Customer.class, "1");
    Assertions.assertNotNull(customer);
    Assertions.assertEquals("1", customer.getId());
    Assertions.assertEquals("John", customer.getName());

    entityTransaction.commit();
    entityManager.close();
  }

  @Test
  @Disabled
  void update() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    // mencari data berdasarkan primary key
    Customer customer = entityManager.find(Customer.class, "1");
    Assertions.assertNotNull(customer);
    Assertions.assertEquals("1", customer.getId());
    Assertions.assertEquals("John", customer.getName());

    customer.setName("Eko Kurniawan");

    // update data
    entityManager.merge(customer);

    entityTransaction.commit();
    entityManager.close();
  }

  @Test
  void remove() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    // mencari data berdasarkan primary key
    Customer customer = entityManager.find(Customer.class, "1");
    Assertions.assertNotNull(customer);
    Assertions.assertEquals("1", customer.getId());

    // hapus data
    entityManager.remove(customer);

    entityTransaction.commit();
    entityManager.close();
  }
}
