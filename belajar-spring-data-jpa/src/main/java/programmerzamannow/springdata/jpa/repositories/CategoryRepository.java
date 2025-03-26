package programmerzamannow.springdata.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmerzamannow.springdata.jpa.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
