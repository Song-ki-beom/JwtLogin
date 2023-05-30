package com.example.SSUtudyLogin.DTO;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@Data
public class UserDeleteDTO {
    @NotNull
    private String student_Id;

    @NotNull
    private String password;




    public UserDeleteDTO(String student_Id, String password) {
        this.student_Id = student_Id;
        this.password = password;

    }
}