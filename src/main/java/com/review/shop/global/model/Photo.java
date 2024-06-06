package com.review.shop.global.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Photo extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String url;
    private String filename;
}
