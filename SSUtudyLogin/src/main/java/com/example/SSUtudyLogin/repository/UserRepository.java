package com.example.SSUtudyLogin.repository;

import com.example.SSUtudyLogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
