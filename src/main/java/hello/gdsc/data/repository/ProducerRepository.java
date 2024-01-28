package hello.gdsc.data.repository;

import hello.gdsc.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Category, Long> {
}
