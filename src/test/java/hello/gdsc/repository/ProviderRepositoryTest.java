package hello.gdsc.repository;

import hello.gdsc.data.entity.Product;
import hello.gdsc.data.entity.Provider;
import hello.gdsc.data.repository.ProductRepository;
import hello.gdsc.data.repository.ProviderRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1() {
        Provider provider = new Provider();
        provider.setName("GDSC용품");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        System.out.println("product : " + productRepository.findById(1L).orElseThrow(RuntimeException::new));

        System.out.println("product : " + productRepository.findById(1L).orElseThrow(RuntimeException::new).getProvider());

    }


    //다대일 양방향 매핑
    @Test
    void relationshipTest() {
        Provider provider = new Provider();
        provider.setName("GDSC용품");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(2000);
        product1.setStock(100);
        product1.setProvider(provider);

        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(25000);
        product2.setStock(200);
        product2.setProvider(provider);

        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3000);
        product3.setStock(1000);
        product3.setProvider(provider);

        productRepository.save(product3);

        //무시 - 연관괸계의 주인이 아니기 떄문에 외래키 관리 불가
        provider.getProductList().add(product1);

        List<Product> products = providerRepository.findById(provider.getId()).get().getProducts();

        for(Product product : products){
            System.out.println(product);
        }

    }

    //영속성 전이 테스트
    @Test
    void cascadeTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000, 1000);
        Product product2 = savedProduct("상품2", 500, 1500);
        Product product3 = savedProduct("상품3", 750, 500);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);
    }

    //고아 객체 테스트
    @Test
    @Transactional
    void orphanRemovalTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000, 1000);
        Product product2 = savedProduct("상품2", 500, 1500);
        Product product3 = savedProduct("상품3", 750, 500);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));
        providerRepository.save(provider);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

    }

    private Provider savedProvider(String name){
        Provider provider = new Provider();
        provider.setName(name);

        return provider;
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }
}

