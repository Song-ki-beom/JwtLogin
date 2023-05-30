package com.example.SSUtudyLogin.DTO;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@Data
public class UserModifyDTO {
    @NotNull
    private String student_Id;


    private String password;


    private String name;

    private Integer grade;


    private String department;


    private List<String> categories = new ArrayList<>();


    public UserModifyDTO(String student_Id, String password, String name, int grade, String department, List<String> categories) {
        this.student_Id = student_Id;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.categories = categories;
    }
}