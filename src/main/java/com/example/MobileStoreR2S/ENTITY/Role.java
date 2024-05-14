package com.example.MobileStoreR2S.ENTITY;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Long id;

    @Column(name = "roleName")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}