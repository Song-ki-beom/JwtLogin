package com.example.SSUtudyLogin.DTO;

import com.sun.istack.NotNull;
import lombok.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
public class LectureResponseDTO {

    private List<String>  textList= new ArrayList<>();



}