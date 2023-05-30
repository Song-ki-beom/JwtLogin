package com.example.SSUtudyLogin.controller;

import com.example.SSUtudyLogin.DTO.*;
import com.example.SSUtudyLogin.service.LectureService;
import com.example.SSUtudyLogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {


    @Lazy
    private final UserService userService;

    // 회원가입
    @PostMapping("/api/v1/users")
    public UserJoinDTO join(@RequestBody UserJoinDTO userRequestDto) {
        Long id= userService.join(userRequestDto);
        return userRequestDto;

    }
    // 로그인
    @PostMapping("/api/v1/users/login")
    public String login(@RequestBody UserLogInDTO userRequestDto) {
        String token = userService.login(userRequestDto);
        return token;
    }

    @PutMapping("/api/v1/users/update")
    public UserModifyDTO update(@RequestBody UserModifyDTO userRequestDTO){


        return  userService.Update(userRequestDTO);
    }

    @DeleteMapping("/api/v1/users/delete")
    public Map<String, Object> delete(@RequestBody UserDeleteDTO userDeleteDTO) {
        Map<String, Object> response = new HashMap<>();

        if(userService.delete(userDeleteDTO) > 0) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
        }

        return response;
    }

    @GetMapping("/api/v1/users/{userId}")
    public UserFindDTO findUser(@PathVariable String userId) {
        return userService.findByStudentId(userId);
    }


    @PostMapping("/authtest")
    public String authtest() {


        return "auth success";
    }





}
