package programmerzamannow.springdata.jpa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import programmerzamannow.springdata.jpa.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory_Name(String name);

    List<Product> findAllByCategory_Name(String name, Sort sort);

    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

    Long countByCategory_Name(String name);

    boolean existsByName(String name);

    @Transactional
    int deleteByName(String name);

    // named query
    List<Product> searchProductUsingName(@Param("name") String name);
}
