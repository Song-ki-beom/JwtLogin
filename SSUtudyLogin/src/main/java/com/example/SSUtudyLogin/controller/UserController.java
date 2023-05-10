package com.example.SSUtudyLogin.controller;

import com.example.SSUtudyLogin.domain.User;
import com.example.SSUtudyLogin.repository.JwtTokenProvider;
import com.example.SSUtudyLogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        return userRepository.save(User.builder()
                .authId(user.get("authId"))
                .passwd(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByAuthId(user.get("authId"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 id 입니다."));
        if (!passwordEncoder.matches(user.get("passwd"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }


}
