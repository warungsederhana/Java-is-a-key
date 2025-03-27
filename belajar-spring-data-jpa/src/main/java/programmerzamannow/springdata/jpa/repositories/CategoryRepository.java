package programmerzamannow.springdata.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmerzamannow.springdata.jpa.entities.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // where name = ?
    Optional<Category> findFirstByNameEquals(String name);

    // where name like ?
    List<Category> findAllByNameLike(String name);
}
