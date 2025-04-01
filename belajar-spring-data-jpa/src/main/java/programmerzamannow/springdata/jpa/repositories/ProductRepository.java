package programmerzamannow.springdata.jpa.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import programmerzamannow.springdata.jpa.entities.Category;
import programmerzamannow.springdata.jpa.entities.Product;
import programmerzamannow.springdata.jpa.models.SimpleProduct;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByCategory_Name(String name);

    List<Product> findAllByCategory_Name(String name, Sort sort);

    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

    Long countByCategory_Name(String name);

    boolean existsByName(String name);

    @Transactional
    int deleteByName(String name);

    // named query
    List<Product> searchProductUsingName(@Param("name") String name);

    // pageable named query
    List<Product> searchProductUsingName(@Param("name") String name, Pageable pageable);

    // query annotation
    @Query(value = "select p from Product p where p.name like :name or p.category.name like :name")
    List<Product> searchProduct(@Param("name") String name);

    // query annotation with pagable and sort
    @Query(
        value = "select p from Product p where p.name like :name or p.category.name like :name",
        countQuery = "select count(p) from Product p where p.name like :name or p.category.name like :name"
    )
    Page<Product> searchProduct(@Param("name") String name, Pageable pageable);

    @Modifying
    @Query(
        value = "delete from Product p where p.name = :name"
    )
    int deleteProductUsingName(@Param("name") String name);

    @Modifying
    @Query(
        value = "update Product p set p.price = 0 where p.id = :id"
    )
    int updateProductPriceToZero(@Param("id") Long id);


    // stream query method
    Stream<Product> streamAllByCategory(Category category);

    // slice query method
    Slice<Product> findAllByCategory(Category category, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findFirstByIdEquals(Long id);

    <T> List<T> findAllByNameLike(String name, Class<T> tClass);
}
