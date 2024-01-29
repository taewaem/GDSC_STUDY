package hello.gdsc.repository;

import hello.gdsc.data.entity.Product;
import hello.gdsc.data.repository.ProductRepository;
import hello.gdsc.data.repository.QProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class QProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @BeforeEach
    void setPro(){

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreateAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(500);
        product2.setCreateAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);

        Product product3 = new Product();
        product1.setName("펜");
        product1.setPrice(2000);
        product1.setStock(200);
        product1.setCreateAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product4 = new Product();
        product2.setName("펜");
        product2.setPrice(4000);
        product2.setStock(400);
        product2.setCreateAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product5 = new Product();
        product1.setName("펜");
        product1.setPrice(2500);
        product1.setStock(500);
        product1.setCreateAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product6 = new Product();
        product2.setName("펜");
        product2.setPrice(6000);
        product2.setStock(600);
        product2.setCreateAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product savedProduct3 = productRepository.save(product3);
        Product savedProduct4 = productRepository.save(product4);
        Product savedProduct5 = productRepository.save(product5);
        Product savedProduct6 = productRepository.save(product6);


        @Autowired
        QProductRepository qProductRepository;
        @Test
        public void queryDSLTest() {
            QProduct qProduct = Qproduct.product;
            Iterable<Product> productList = qProductRepository.findAll(
                    qProduct.name.contains("펜")
                            .and(qProduct.price.between(2000,2500))
            );

            for(Product product : productList){
                System.out.println("----------------------------------------");
                System.out.println(product.getName());
                System.out.println(product.getNumber());
                System.out.println(product.getPrice());
                System.out.println(product.getStock());
                System.out.println("----------------------------------------");

            }

        }
    }

}
