package com.example.SSUtudyLogin.repository;
import com.example.SSUtudyLogin.domain.Category;
import com.example.SSUtudyLogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CategoryRepository  extends JpaRepository<Category,Long>{
    Optional<Category> findByid(String id);

}
