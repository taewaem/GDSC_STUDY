package hello.gdsc.data.repository;

import hello.gdsc.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
QProductRepository extends JpaRepository<Product, Long> {

}