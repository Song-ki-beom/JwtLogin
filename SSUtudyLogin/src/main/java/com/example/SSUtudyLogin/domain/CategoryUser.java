package com.example.SSUtudyLogin.domain;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
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


    public CategoryUser(User user, Category category) {
         this.user =  user;
         this.category = category;

    }
}