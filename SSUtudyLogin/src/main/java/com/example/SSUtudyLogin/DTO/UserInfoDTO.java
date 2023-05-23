package com.example.SSUtudyLogin.DTO;

import com.example.SSUtudyLogin.domain.CategoryUser;
import com.example.SSUtudyLogin.domain.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserInfoDTO {

    private String student_Id;


    private String password;


    private String name;

    private int grade;


    private String department;


    private List<CategoryUser> categories = new ArrayList<>();


}