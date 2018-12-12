package com.holidaysomething.holidaysomething.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_OPTION")
@Getter
@Setter
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer price;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
