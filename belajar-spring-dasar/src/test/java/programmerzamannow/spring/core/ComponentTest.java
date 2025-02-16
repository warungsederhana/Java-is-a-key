package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.repository.CategoryRepository;
import programmerzamannow.spring.core.repository.CustomerRepository;
import programmerzamannow.spring.core.repository.ProductRepository;
import programmerzamannow.spring.core.service.CategoryService;
import programmerzamannow.spring.core.service.CustomerService;
import programmerzamannow.spring.core.service.ProductService;

public class ComponentTest {
  private ConfigurableApplicationContext context;

  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(ComponentConfiguration.class);
    context.registerShutdownHook();
  }

  @Test
  void testScan() {
    ProductService productService= context.getBean(ProductService.class);
    ProductService productService2= context.getBean("productService", ProductService.class);


    Assertions.assertSame(productService, productService2);
    System.out.println(productService);
  }

  @Test
  void testConstructorDependencyInjection() {
    ProductService productService = context.getBean(ProductService.class);
    ProductRepository productRepository = context.getBean(ProductRepository.class);

    Assertions.assertNotNull(productService.getProductRepository());
    Assertions.assertSame(productRepository, productService.getProductRepository());
  }

  @Test
  void testSetterDependencyInjection() {
    CategoryService categoryService = context.getBean(CategoryService.class);
    CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

    Assertions.assertNotNull(categoryService.getCategoryRepository());
    Assertions.assertSame(categoryRepository, categoryService.getCategoryRepository());
  }

  @Test
  @Disabled
  void testFieldDependencyInjection() {
    // use CustomerRepository with uncommenting annotation Component in CustomerRepository
    CustomerService customerService = context.getBean(CustomerService.class);
    CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

//    Assertions.assertNotNull(customerService.getCustomerRepository());
//    Assertions.assertSame(customerRepository, customerService.getCustomerRepository());
  }

  @Test
  void testQualifier() {
    CustomerService customerService = context.getBean(CustomerService.class);
    CustomerRepository normalCustomerRepository = context.getBean("normalCustomerRepository", CustomerRepository.class);
    CustomerRepository vipCustomerRepository = context.getBean("vipCustomerRepository", CustomerRepository.class);

    Assertions.assertNotNull(customerService.getNormalcustomerRepository());
    Assertions.assertSame(normalCustomerRepository, customerService.getNormalcustomerRepository());

    Assertions.assertNotNull(customerService.getVipCustomerRepository());
    Assertions.assertSame(vipCustomerRepository, customerService.getVipCustomerRepository());
  }
}
