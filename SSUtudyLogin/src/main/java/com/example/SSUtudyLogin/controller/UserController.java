package com.example.SSUtudyLogin.controller;

import com.example.SSUtudyLogin.DTO.UserInfoDTO;
import com.example.SSUtudyLogin.domain.User;
import com.example.SSUtudyLogin.repository.JwtTokenProvider;
import com.example.SSUtudyLogin.service.CustomUserDetailService;
import com.example.SSUtudyLogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {


    @Lazy
    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody UserInfoDTO userRequestDto) {
        Long id= userService.join(userRequestDto);
        return id;

    }
    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody UserInfoDTO userRequestDto) {
        String token = userService.login(userRequestDto);
        return token;
    }


    @PostMapping("/authtest")
    public String authtest() {


        return "auth success";
    }


}
