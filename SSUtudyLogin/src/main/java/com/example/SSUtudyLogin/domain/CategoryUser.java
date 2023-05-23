package com.example.SSUtudyLogin.domain;



import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CategoryUser {

    @Id @GeneratedValue
    @Column(name = "category_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}