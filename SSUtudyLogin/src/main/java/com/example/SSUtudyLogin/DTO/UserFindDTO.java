package com.example.SSUtudyLogin.DTO;

import com.example.SSUtudyLogin.domain.User;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@Data
public class UserFindDTO {

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


    public UserFindDTO(String student_Id, String password, String name, int grade, String department, List<String> categories) {
        this.student_Id = student_Id;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.categories = categories;
    }
    public UserFindDTO(User user,List<String> categories_string) {
        this.student_Id = user.getStudentId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.grade = user.getGrade();
        this.department = user.getDepartment();
        this.categories = categories_string;
    }
}
