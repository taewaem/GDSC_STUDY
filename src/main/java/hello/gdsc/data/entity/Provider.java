package hello.gdsc.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    //다대일 양방향 매핑
//    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER) //즉시로딩 설정
//    @ToString.Exclude
//    private List<Product> productList = new ArrayList<>();
//
//    //영속성 전이
//    @OneToMany(mappedBy = "provider", cascade = CascadeType.PERSIST)
//    @ToString.Exclude
//    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();
}
