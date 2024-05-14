package com.example.MobileStoreR2S.ENTITY;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private long id;

    @Column(name = "productName", length = 256)
    private String name;

    @Column(name = "unitPrice") // Ví dụ precision và scale
    private Double unitPrice;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "unitStock")
    private long unitStock;

    @Column(name = "manufacturer", length = 256)
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition")
    private Condition condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images;



}
