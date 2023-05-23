package com.example.SSUtudyLogin.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 100, nullable = false, unique = true)
    @NotNull
    private String studentId;

    @Column(length = 300, nullable = false)
    @NotNull
    private String password;

    @Column(length = 300, nullable = false)
    private String name;

    @Column(length = 300, nullable = false)
    private int grade;

    @Column(length = 300, nullable = false)
    private String department;


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @NotNull
    private List<String> roles = new ArrayList<>();

    //@ElementCollection(fetch = FetchType.EAGER)
    //@CollectionTable(name="category_Users", joinColumns = @JoinColumn(name= "id", referencedColumnName = "id"))
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CategoryUser> categoryUsers = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return studentId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
