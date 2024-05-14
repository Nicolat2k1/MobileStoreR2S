package com.example.MobileStoreR2S.ENTITY;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private long id;

    @Column(name = "categoryName", length = 256)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
