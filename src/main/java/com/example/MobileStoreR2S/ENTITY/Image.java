package com.example.MobileStoreR2S.ENTITY;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private long id;

    @Column(name = "imageName")
    private String name;

    @Column(name = "imageUrl")
    private String url;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;
}