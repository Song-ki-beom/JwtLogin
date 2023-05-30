package com.example.SSUtudyLogin.repository;
import com.example.SSUtudyLogin.domain.Category;
import com.example.SSUtudyLogin.domain.CategoryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CategoryUserRepository extends JpaRepository<CategoryUser,Long>{
    Optional<CategoryUser> findById(Long id);

    Optional<CategoryUser> findByUserId(long user_id);
    void deleteByUserId(long user_id);



}
