package com.example.SSUtudyLogin.DTO;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@Data
public class UserJoinDTO {
    @NotNull
    private String student_Id;

    @NotNull
    private String password;

    @NotNull
    private String name;
    @NotNull
    private int grade;

    @NotNull
    private String department;

    @NotNull
    private List<String> categories = new ArrayList<>();


    public UserJoinDTO(String student_Id, String password, String name, int grade, String department, List<String> categories) {
        this.student_Id = student_Id;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.categories = categories;
    }
}