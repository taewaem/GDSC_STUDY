package hello.gdsc.repository;

import hello.gdsc.data.entity.Product;
import hello.gdsc.data.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    void save() {
        //given
        Product product = new Product();
        product.setName("펜");
        product.setStock(1000);
        product.setPrice(1000);

        //when
        Product savedPrduct = productRepository.save();

        //then
        assertEquals(product.getName(), savedPrduct.getName());
        assertEquals(product.getStock(), savedPrduct.getStock());
        assertEquals(product.getPrice(), savedPrduct.getPrice());
    }


}
