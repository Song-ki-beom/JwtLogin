package com.example.SSUtudyLogin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    @Column(length = 100, nullable = false, unique = true)
    private String loginId;

    @Column(length = 300, nullable = false)
    private String passwd;
}
