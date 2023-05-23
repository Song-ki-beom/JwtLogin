package com.example.SSUtudyLogin.service;

import com.example.SSUtudyLogin.DTO.UserInfoDTO;
import com.example.SSUtudyLogin.domain.User;
import com.example.SSUtudyLogin.repository.JwtTokenProvider;
import com.example.SSUtudyLogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Lazy
    private  final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findBystudentId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

    }



}


