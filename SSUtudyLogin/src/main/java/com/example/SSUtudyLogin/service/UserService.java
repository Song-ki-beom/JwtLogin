package com.example.SSUtudyLogin.service;

import com.example.SSUtudyLogin.DTO.UserInfoDTO;
import com.example.SSUtudyLogin.domain.Category;
import com.example.SSUtudyLogin.domain.CategoryUser;
import com.example.SSUtudyLogin.domain.User;
import com.example.SSUtudyLogin.repository.CategoryRepository;
import com.example.SSUtudyLogin.repository.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.SSUtudyLogin.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Map;



@RequiredArgsConstructor
@Service
public class UserService {
    @Lazy
    private  final UserRepository userRepository;

    @Lazy
    private  final CategoryRepository categoryRepository;

    @Lazy
    private final PasswordEncoder passwordEncoder;
    @Lazy
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public Long join(UserInfoDTO userInfoDTO) {
        List<String> categoryList=  userInfoDTO.getCategories();
        for(String i: categoryList){

            CategoryUser catUser = new CategoryUser(userRepository.findBystudentId(userInfoDTO.getStudent_Id()),
                    Category category);
        }
        return userRepository.save(User.builder()
                .studentId(userInfoDTO.getStudent_Id())
                .password(passwordEncoder.encode(userInfoDTO.getPassword()))
                .name(userInfoDTO.getName())
                .grade(userInfoDTO.getGrade())
                .department(userInfoDTO.getDepartment())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .categoryUsers(userInfoDTO.getCategories())
                .build()).getId();


    }


    @Transactional
    public String login(UserInfoDTO userInfoDTO) {
        User member = userRepository.findBystudentId(userInfoDTO.getStudent_Id())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 id 입니다."));
        if (!passwordEncoder.matches(userInfoDTO.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return  jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }



}
