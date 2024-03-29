package hello.gdsc.repository;

import hello.gdsc.data.entity.Product;
import hello.gdsc.data.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductRepositoryTest2 {
    @Autowired
    private ProductRepository productRepository;


    @Test
    void basicCRUDTest() {
        //create
        //given
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build;

        //when
        Product savedProduct = ProductRepository.save(givenProduct);

        //thne
        Assertions.assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
        Assertions.assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

        //read
        Product selectedProduct = productRepository.findById(savedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        //then
        Assertions.assertThat(selectedProduct.getNumber())
                .isEqualTo(givenProduct.getNumber());
        Assertions.assertThat(selectedProduct.getName())
                .isEqualTo(givenProduct.getName());
        Assertions.assertThat(selectedProduct.getPrice())
                .isEqualTo(givenProduct.getPrice());
        Assertions.assertThat(selectedProduct.getStock())
                .isEqualTo(givenProduct.getStock());

        //update
        //when
        Product foundProduct = productRepository.findById(selectedProduct.getNumber())
                .orElseThrow(RuntimeException::new);

        foundProduct.setName("장난감");

        Product updateProduct = productRepository.save(foundProduct);

        //then
        assertEquals(foundProduct.getName(), "장난감");

        //delete
        //when
        productRepository.delete(updateProduct);

        //then
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());

    }

}
